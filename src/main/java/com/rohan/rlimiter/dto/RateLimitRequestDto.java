package com.rohan.rlimiter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RateLimitRequestDto {
    private String path;
    private String method;
    private String clientId;
}
