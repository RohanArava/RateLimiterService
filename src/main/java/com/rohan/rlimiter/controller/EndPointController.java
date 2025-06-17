package com.rohan.rlimiter.controller;

import com.rohan.rlimiter.dto.EndPointRequestDto;
import com.rohan.rlimiter.dto.EndPointResponseDto;
import com.rohan.rlimiter.model.Application;
import com.rohan.rlimiter.model.EndPoint;
import com.rohan.rlimiter.service.ApplicationService;
import com.rohan.rlimiter.service.EndPointService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class EndPointController {
    private final EndPointService service;
    private final ApplicationService applicationService;

    @PostMapping("/applications/{apiKey}/endpoints")
    public ResponseEntity<EndPointResponseDto> createEndPoint(@PathVariable String apiKey, @RequestBody EndPointRequestDto request) {
        Application application = applicationService.getApplicationByApiKey(apiKey).orElse(null);
        if (application == null) {
            return ResponseEntity.notFound().build();
        }
        EndPoint endPoint = service.saveEndPoint(application, request.getPath(), request.getMethod(), request.getRateLimit(), request.getDuration()).orElse(null);
        if (endPoint == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(new EndPointResponseDto(endPoint.getPath(), endPoint.getMethod(), endPoint.getRateLimit(), endPoint.getDuration(), application.getName()));
    }

    @Transactional
    @DeleteMapping("/applications/{apiKey}/endpoints")
    public ResponseEntity<?> deleteEndPoint(@PathVariable String apiKey, @RequestBody EndPointRequestDto request){
        Application application = applicationService.getApplicationByApiKey(apiKey).orElse(null);
        if (application == null) {
            return ResponseEntity.notFound().build();
        }
        Long num = service.deleteEndPoint(application, request.getPath(), request.getMethod());
        if (num == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Successfully deleted " + num + " endpoints");
    }


}
