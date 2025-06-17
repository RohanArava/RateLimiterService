package com.rohan.rlimiter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ApiKeyDto {
    private String apiKey;
    private String message;
}
