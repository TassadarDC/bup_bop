package com.pinger.automation.core.factories;

import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.input.InputDataDto;
import com.pinger.automation.core.model.entites.dto.input.PingerInputDataFile;
import com.pinger.automation.core.model.entites.dto.output.PingerOutputDataFile;
import com.pinger.automation.core.model.enums.Endpoint;
import com.pinger.automation.utils.FileUtils;
import com.pinger.automation.utils.PingerConfig;

public class PingerTestDataFactory {
    private static final PingerInputDataDtoFactory PINGER_INPUT_DATA_DTO_FACTORY = new PingerInputDataDtoFactory();
    public static TestDataDto createTestDataDto(Class clazz, Endpoint endpoint) {
        //Generate dto for test input data
        InputDataDto inputDto = PINGER_INPUT_DATA_DTO_FACTORY.getPingDataDto(endpoint);
        return createTestDataDto(clazz, inputDto);
    }

    public static TestDataDto createTestDataDto(Class clazz, InputDataDto inputDto) {
        String directory = PingerConfig.getPingerWorkingDirectory();
        String inputFileName = clazz.getSimpleName() + "InputData.json";
        String outputFileName = clazz.getSimpleName() + "OutputData.json";

        //Create json file at provided directory.
        FileUtils.createJsonFileFromDto(inputDto, directory, inputFileName);

        //Fill the rest of DTO wo work with
        PingerInputDataFile inputDataFile = new PingerInputDataFile();
        inputDataFile.setDto(inputDto);
        inputDataFile.setDirectory(directory);
        inputDataFile.setName(inputFileName);

        PingerOutputDataFile outputDataFile = new PingerOutputDataFile();
        outputDataFile.setDto(null);
        outputDataFile.setDirectory(directory);
        outputDataFile.setName(outputFileName);

        TestDataDto dataFile = new TestDataDto();
        dataFile.setInputDataFile(inputDataFile);
        dataFile.setOutputDataFile(outputDataFile);
        return dataFile;
    }
}
