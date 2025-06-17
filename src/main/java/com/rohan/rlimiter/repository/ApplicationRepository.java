package com.rohan.rlimiter.repository;

import com.rohan.rlimiter.dto.ApplicationProjection;
import com.rohan.rlimiter.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByApiKey(String apiKey);

    List<ApplicationProjection> findAllBy();

    Optional<Application> findApplicationByApiKey(String apiKey);

    Long deleteApplicationByApiKey(String apiKey);

//    @Query("SELECT a FROM applications a JOIN a.endPoints e WHERE a.apiKey = :apiKey and e.path = :path and e.method = :method")
//    Optional<Application> findApplicationEndPoint(@Param("apiKey") String apiKey, @Param("path") String path, @Param("method") String method);
}
