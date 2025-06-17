package com.rohan.rlimiter.service;

import com.rohan.rlimiter.dto.ApplicationProjection;
import com.rohan.rlimiter.model.Application;
import com.rohan.rlimiter.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ApplicationService {
    private final ApplicationRepository repository;

    public List<ApplicationProjection> getApplications() {
        return repository.findAllBy();
    }

    public Optional<Application> getApplicationByApiKey(String apiKey){
        return repository.findByApiKey(apiKey);
    }

    public Application saveApplication(String name) {
        String apiKey = UUID.randomUUID().toString();
        Application application = new Application(name, apiKey);
        return repository.save(application);
    }

    public Long deleteApplicationByApiKey(String apiKey){
        return  repository.deleteApplicationByApiKey(apiKey);
    }
}
