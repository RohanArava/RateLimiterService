package com.rohan.rlimiter.service;

import com.rohan.rlimiter.model.Application;
import com.rohan.rlimiter.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApiKeyValidatorService {
    private final ApplicationRepository repository;

    public boolean isValid(String apiKey) {
        return repository.findByApiKey(apiKey).isPresent();
    }

    public Application getApplication(String apiKey) {
        return repository.findByApiKey(apiKey).orElse(null);
    }
}