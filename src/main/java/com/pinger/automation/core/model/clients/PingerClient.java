package com.pinger.automation.core.model.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.config.ConfigDto;
import com.pinger.automation.core.model.entites.dto.report.EntryDto;
import com.pinger.automation.core.model.entites.dto.report.ReportDto;
import com.pinger.automation.utils.FileUtils;
import com.pinger.automation.utils.JsonUtils;
import com.pinger.automation.utils.SoftVerifier;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import static com.pinger.automation.utils.PingerAppConfig.getPingerExecutable;
import static com.pinger.automation.utils.PingerAppConfig.getPingerWorkingDirectory;

@Slf4j
public class PingerClient extends ExecutableClient<ReportDto> {
    private static final String EXECUTABLE = getPingerExecutable();
    private static final String WORKING_DIRECTORY = getPingerWorkingDirectory();
    private final TestDataDto TEST_DATA_DTO;

    public PingerClient(TestDataDto dto) {
        super(EXECUTABLE, WORKING_DIRECTORY, dto.getConfig().getName(), dto.getReport().getName());
        TEST_DATA_DTO = dto;
    }

    @Override
    public ReportDto processValidScenario() {
        OffsetDateTime expectedStartDateTime = OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        execute();
        OffsetDateTime expectedEndDateTime = OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        //Get an object with expected data.
        ConfigDto expected = TEST_DATA_DTO.getConfig().getDto();

        //Get an object with actual data after execution
        ReportDto actual = FileUtils.parseFileToObject(WORKING_DIRECTORY + TEST_DATA_DTO.getReport().getName(), new TypeReference<>() {
        });

        //Check if report has data.
        if (actual.getEntries() == null || actual.getEntries().isEmpty()) {
            throw new IllegalStateException("No entries found in result.json");
        }
        log.info("Result file: {}", JsonUtils.toJsonString(actual));
        SoftVerifier verifier = new SoftVerifier();

        //Validate timestamps (doesn't work properly, fails from time to time due to one second edge)
        OffsetDateTime reportStartDateTime = OffsetDateTime.parse(actual.getStartTime());
        OffsetDateTime reportEndDateTime = OffsetDateTime.parse(actual.getEndTime());
        verifier.assertNotEquals(reportStartDateTime, reportEndDateTime, "Verify that start and end time differs.");
        verifier.assertTrue(reportStartDateTime.isBefore(reportEndDateTime), "Verify that start date is before end date.");
        verifier.assertEquals(expectedStartDateTime.truncatedTo(ChronoUnit.SECONDS), reportStartDateTime.truncatedTo(ChronoUnit.SECONDS), "Verity start date time.");
        verifier.assertEquals(expectedEndDateTime.truncatedTo(ChronoUnit.SECONDS), reportEndDateTime.truncatedTo(ChronoUnit.SECONDS), "Verity end date time.");

        //Validate min/max pings
        verifier.assertEquals(actual.getMinSuccessfulPings(), expected.getMinSuccessfulPings(), "Verity min successful pings.");
        verifier.assertTrue(actual.getMaxPings() <= expected.getMaxPings(), "Verity max successful pings is less then successful.");

        //Validate that report does not contain duplicates
//        verifier.assertTrue(new HashSet<>(actual.getEntries()).size() == actual.getEntries().size(), "Verify that report does not contain duplicates.");

        //Validate total count of entries (report should contain only values for endpoint where ignore = false)
        verifier.assertTrue(actual.getEntries().stream().map(EntryDto::getEndpoint).toList().containsAll(expected.getEndpoints().stream().filter(x -> !x.isIgnore()).toList()), "Verify that report contains all not ignored endpoints.");// bug

        //Navigate through the list of endpoints to validate all of them.
        validateEndpointEntries(actual, expected, verifier);
        verifier.verifyAll();
        return actual;
    }

    private void validateEndpointEntries(ReportDto actual, ConfigDto expected, SoftVerifier verifier) {
        for (EntryDto actualEntry : actual.getEntries()) {
            //move this to validate list of expected vs list of actual
            EndpointDto expectedEndpoint = expected.getEndpoints().stream().filter(e -> e.getDescription().equals(actualEntry.getEndpoint().getDescription())).findFirst().orElse(null);

            if (expectedEndpoint == null) {
                log.warn("There is no expected {} endpoint in report file.", actualEntry.getEndpoint().getDescription());
                break;
            }
            log.warn("Verifying entry: {}.", actualEntry);

            //TODO uncomment
            //verifier.assertEquals(actualEntry.getEndpoint(), expectedEndpoint, "Assert endpoint data."); - BUG with ignored endpoints
            verifier.assertTrue(actualEntry.getTotalPings() <= expected.getMaxPings(), "Assert actual total vs maximum successful pings.");
            verifier.assertTrue(actualEntry.getSuccessfulPings() <= expected.getMaxPings(), "Successful pings count is >= maximum pings.");

            //Assert actual vs minimum successful pings.
            if (actualEntry.getSuccessfulPings() == 0) {
                log.info("{} is unreachable. Successful pings = 0", actualEntry.getEndpoint().getDescription());
            } else if (actualEntry.getSuccessfulPings() == expected.getMinSuccessfulPings()) {
                log.info("{} is reachable.", actualEntry.getEndpoint().getDescription());
            } else if (actualEntry.getSuccessfulPings() < expected.getMinSuccessfulPings()) {
                log.info("{} failed to get minimum required pings. Needed {}, Actual {}", actualEntry.getEndpoint().getDescription(), expected.getMinSuccessfulPings(), actualEntry.getTotalPings());
            }
        }
    }
}
