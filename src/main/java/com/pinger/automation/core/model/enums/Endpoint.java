package com.pinger.automation.core.model.enums;

import com.pinger.automation.core.model.entites.dto.EndpointDto;
import lombok.Getter;

@Getter
public enum Endpoint {
    CLOUDFLARE_DNS("1.1.1.1", "Cloudflare DNS", false),
    CLOUDFLARE_SECONDARY_DNS("1.0.0.1", "Cloudflare Secondary DNS", false),
    GOOGLE_DNS("8.8.8.8", "Google DNS", false),
    GOOGLE_SECONDARY_DNS("8.8.4.4", "Google Secondary DNS", false),
    GOOGLE_DNS_EDGE("8.8.8.8", "Google DNS EDGE", false),
    MY_ROUTER("192.168.0.1", "My Router", false),
    MY_SO_LAN("192.168.0.102", "My S/O LAN", false),
    MY_SO_WAN("123.123.123.123", "My S/O WAN", false),
    OPEN_DNS("208.67.222.222", "Open DNS", false),
    Quad9_DNS("9.9.9.9", "Quad9 DNS", false),
    Quad9_SECONDARY_DNS("149.112.112.112", "Quad9 DNS", false),
    REACHABLE_IGNORED("1.1.1.1", "Cloudflare DNS", true),
    UNREACHABLE("192.168.0.101", "My Grandma", false),
    UNREACHABLE_IGNORED("192.168.0.101", "My Grandma", true);

    private final String address;
    private final String description;
    private final boolean ignore;

    Endpoint(String address, String description, boolean ignore) {
        this.address = address;
        this.description = description;
        this.ignore = ignore;
    }
}
