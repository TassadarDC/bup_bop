package com.pinger.automation.core.factories;

import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.config.ConfigDto;
import com.pinger.automation.core.model.entites.dto.config.PingerConfigFile;
import com.pinger.automation.core.model.entites.dto.report.PingerReportFile;
import com.pinger.automation.core.model.enums.Endpoint;
import com.pinger.automation.utils.FileUtils;
import com.pinger.automation.utils.PingerAppConfig;

public class PingerTestDataFactory {
    private static final PingerConfigDataDtoFactory PINGER_CONFIG_DATA_DTO_FACTORY = new PingerConfigDataDtoFactory();
    public static TestDataDto createTestDataDto(Class clazz, Endpoint endpoint) {
        //Generate dto for test config data
        ConfigDto configDto = PINGER_CONFIG_DATA_DTO_FACTORY.getPingDataDto(endpoint);
        return createTestDataDto(clazz, configDto);
    }

    public static TestDataDto createTestDataDto(Class clazz, ConfigDto configDto) {
        String directory = PingerAppConfig.getPingerWorkingDirectory();
        String configName = clazz.getSimpleName() + "Config.json";
        String reportName = clazz.getSimpleName() + "Report.json";

        //Create json file at provided directory.
        FileUtils.createJsonFileFromDto(configDto, directory, configName);

        //Fill the rest of DTO wo work with
        PingerConfigFile pingerConfigFile = new PingerConfigFile();
        pingerConfigFile.setDto(configDto);
        pingerConfigFile.setDirectory(directory);
        pingerConfigFile.setName(configName);

        PingerReportFile reportFile = new PingerReportFile();
        reportFile.setDto(null);
        reportFile.setDirectory(directory);
        reportFile.setName(reportName);

        TestDataDto dataFile = new TestDataDto();
        dataFile.setConfig(pingerConfigFile);
        dataFile.setReport(reportFile);
        return dataFile;
    }
}
