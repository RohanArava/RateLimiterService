package com.rohan.rlimiter.repository;

import com.rohan.rlimiter.model.Application;
import com.rohan.rlimiter.model.EndPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EndPointRepository  extends JpaRepository<EndPoint, Long> {
    Optional<EndPoint> findByApplicationAndPathAndMethod(Application application, String path, String method);
    Long deleteEndPointByApplicationAndPathAndMethod(Application application, String path, String method);
}
