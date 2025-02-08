package com.pinger.automation.core.model.clients;

import com.pinger.automation.utils.AppRunner;

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
        return AppRunner.runApplication(EXECUTABLE, WORKING_DIRECTORY, DATA_FILE_NAME, RESULTS_FILE_NAME);
    }
}
