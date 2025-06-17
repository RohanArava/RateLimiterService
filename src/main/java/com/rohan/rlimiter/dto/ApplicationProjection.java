package com.rohan.rlimiter.dto;

import java.util.List;

public interface ApplicationProjection {
    String getName();
    String getApiKey();
    List<EndPointProjection> getEndPoints();
}
