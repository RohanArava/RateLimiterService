package com.rohan.rlimiter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RateLimitResponseDto {
    private boolean isRequestAllowed;
    private Long millisToRefill;
    private int requestsRemaining;
    private String message;
}
