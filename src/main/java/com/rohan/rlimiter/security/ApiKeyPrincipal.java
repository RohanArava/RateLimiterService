package com.rohan.rlimiter.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiKeyPrincipal {
    private final Long appId;
    private final String appName;

}