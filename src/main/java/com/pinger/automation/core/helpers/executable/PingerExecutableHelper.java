package com.pinger.automation.core.helpers.executable;

import com.pinger.automation.core.clients.PingerClient;
import com.pinger.automation.core.model.entites.dto.TestDataDto;

public final class PingerExecutableHelper {
    public static PingerClient getPingerClient(TestDataDto dto) {
        return new PingerClient(dto);
    }
}
