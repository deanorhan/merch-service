package org.daemio.merch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class RoleConfig {
    
    @Value("${app.security.role.admin:ADMIN}")
    private String admin;
    @Value("${app.security.role.admin:FAN}")
    private String fan;
    @Value("${app.security.role.vendor:VENDOR}")
    private String vendor;
}
