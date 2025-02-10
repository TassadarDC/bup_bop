package com.pinger.automation.core.clients;

import com.pinger.automation.utils.AppRunner;
import io.qameta.allure.Allure;

public abstract class ExecutableClient<O> {
    private final String EXECUTABLE;
    private final String WORKING_DIRECTORY;
    private final String DATA_FILE_NAME;
    private final String RESULTS_FILE_NAME;

    public ExecutableClient(String executable, String directoryPath, String dataFileName, String resultsFileName) {
        EXECUTABLE = executable;
        WORKING_DIRECTORY = directoryPath;
        DATA_FILE_NAME = dataFileName;
        RESULTS_FILE_NAME = resultsFileName;
    }

    public abstract O processValidScenario();

    public String execute() {
        Allure.step(String.format("Running %s application with %s %s parameters.", EXECUTABLE, DATA_FILE_NAME, RESULTS_FILE_NAME));
        return AppRunner.runApplication(EXECUTABLE, WORKING_DIRECTORY, DATA_FILE_NAME, RESULTS_FILE_NAME);
    }
}
