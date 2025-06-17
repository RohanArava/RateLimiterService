package com.rohan.rlimiter.controller;

import com.rohan.rlimiter.dto.RateLimitRequestDto;
import com.rohan.rlimiter.dto.RateLimitResponseDto;
import com.rohan.rlimiter.model.Application;
import com.rohan.rlimiter.model.EndPoint;
import com.rohan.rlimiter.service.ApplicationService;
import com.rohan.rlimiter.service.EndPointService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
public class RateLimitController {
    private ApplicationService applicationService;
    private EndPointService endPointService;
    private RedisTemplate<String,Integer> redisTemplate;

    @PostMapping("/limit")
    ResponseEntity<RateLimitResponseDto> rateLimit(@RequestHeader("X-API-KEY") String apiKey, @RequestBody RateLimitRequestDto request){
        Application application = applicationService.getApplicationByApiKey(apiKey).orElse(null);
        if (application == null) {
            System.out.println("Application not found");
            return ResponseEntity.notFound().build();
        }
        EndPoint endPoint = endPointService.getEndPoint(application, request.getPath(), request.getMethod()).orElse(null);
        if (endPoint == null) {
            System.out.println("end point not found");
            return ResponseEntity.notFound().build();
        }
        String cacheKey = endPoint.getId() + ":" + request.getClientId();
        if(redisTemplate.hasKey(cacheKey)){
            try {
                int remainingLimit = endPoint.getRateLimit() - redisTemplate.opsForValue().get(cacheKey);
                if (remainingLimit <= 0) {
                    return ResponseEntity.ok(new RateLimitResponseDto(false, redisTemplate.getExpire(cacheKey, TimeUnit.MILLISECONDS), 0,
                            "Request limit reached"));
                } else {
                    redisTemplate.opsForValue().increment(cacheKey);
                    return ResponseEntity.ok(new RateLimitResponseDto(true, redisTemplate.getExpire(cacheKey, TimeUnit.MILLISECONDS), remainingLimit - 1,
                            "Request allowed"));
                }
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            redisTemplate.opsForValue().set(cacheKey, 1);
            redisTemplate.expire(cacheKey, endPoint.getDuration(), TimeUnit.MILLISECONDS);
            return ResponseEntity.ok(new RateLimitResponseDto(true, redisTemplate.getExpire(cacheKey, TimeUnit.MILLISECONDS), endPoint.getRateLimit() - 1,
                    "Request allowed"));
        }
    }
}
