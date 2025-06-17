package com.rohan.rlimiter.dto;

public interface EndPointProjection {
    String getPath();
    String getMethod();
    String getRateLimit();
    String getDuration();
}
