package com.rohan.rlimiter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EndPointResponseDto {
    private String path;
    private String method;
    private int rateLimit;
    private int duration;
    private String applicationName;
}
