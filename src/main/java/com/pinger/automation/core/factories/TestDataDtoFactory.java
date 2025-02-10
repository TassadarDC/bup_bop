package com.pinger.automation.core.factories;

import com.pinger.automation.core.model.entites.dto.FileData;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.config.ConfigDto;
import com.pinger.automation.core.model.entites.dto.report.ReportDto;
import com.pinger.automation.core.model.enums.Endpoint;
import com.pinger.automation.utils.PingerAppConfig;
import com.pinger.automation.utils.PingerFileUtils;
import io.qameta.allure.Allure;

public final class TestDataDtoFactory {

    public static <T> TestDataDto createTestDataDto(Class<T> clazz, Endpoint endpoint) {
        //Generate dto for test config data
        ConfigDto configDto = ConfigDataDtoFactory.getConfigDataDto(endpoint);
        return createTestDataDto(clazz, configDto);
    }

    public static <T> TestDataDto createTestDataDto(Class<T> clazz, ConfigDto configDto) {
        Allure.step("Generating test data.");
        String directory = PingerAppConfig.getPingerWorkingDirectory();
        String configName = clazz.getSimpleName() + "Config.json";
        String reportName = clazz.getSimpleName() + "Report.json";

        PingerFileUtils.createJsonFileFromDto(configDto, directory, configName);

        FileData<ConfigDto> pingerConfigFile = getPingerConfigFile(configDto, directory, configName);
        FileData<ReportDto> reportFile = getPingerReportFile(directory, reportName);

        TestDataDto dataFile = new TestDataDto();
        dataFile.setConfig(pingerConfigFile);
        dataFile.setReport(reportFile);
        Allure.step(String.format("Generated %s.", dataFile));
        return dataFile;
    }

    private static FileData<ConfigDto> getPingerConfigFile(ConfigDto configDto, String directory, String configName) {
        FileData<ConfigDto> pingerConfigFile = new FileData<>();
        pingerConfigFile.setDto(configDto);
        pingerConfigFile.setDirectory(directory);
        pingerConfigFile.setName(configName);
        return pingerConfigFile;
    }

    private static FileData<ReportDto> getPingerReportFile(String directory, String reportName) {
        FileData<ReportDto> reportFile = new FileData<>();
        reportFile.setDto(null);
        reportFile.setDirectory(directory);
        reportFile.setName(reportName);
        return reportFile;
    }
}
