package com.rohan.rlimiter.controller;

import com.rohan.rlimiter.dto.ApiKeyDto;
import com.rohan.rlimiter.dto.ApplicationProjection;
import com.rohan.rlimiter.dto.ApplicationRequestDto;
import com.rohan.rlimiter.model.Application;
import com.rohan.rlimiter.service.ApplicationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ApplicationController {

    private final ApplicationService service;

    @PostMapping("/applications")
    public ResponseEntity<ApiKeyDto> createApplication(@RequestBody ApplicationRequestDto request) {
        Application application = service.saveApplication(request.getName());
        ApiKeyDto apiKeyDto = new ApiKeyDto(application.getApiKey(), "Successfully created the application " + application.getName());
        return ResponseEntity.ok(apiKeyDto);
    }

    @GetMapping("/applications")
    public List<ApplicationProjection> getAllApplications() {
        return service.getApplications();
    }

    @GetMapping("/applications/{apiKey}")
    public Optional<Application> getApplication(@PathVariable String apiKey){
        return service.getApplicationByApiKey(apiKey);
    }

    @Transactional
    @DeleteMapping("/applications/{apiKey}")
    public ResponseEntity<ApiKeyDto> deleteApplication(@PathVariable String apiKey){
        Long num = service.deleteApplicationByApiKey(apiKey);
        if(num==0){
            return ResponseEntity.notFound().build();
        }
        ApiKeyDto apiKeyDto = new ApiKeyDto(apiKey, "Successfully deleted the application with apiKey: " + apiKey);
        return ResponseEntity.ok(apiKeyDto);
    }
}