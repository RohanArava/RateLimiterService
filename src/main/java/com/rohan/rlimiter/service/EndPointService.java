package com.rohan.rlimiter.service;

import com.rohan.rlimiter.model.Application;
import com.rohan.rlimiter.model.EndPoint;
import com.rohan.rlimiter.repository.EndPointRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EndPointService {
    private final EndPointRepository repository;

    public Optional<EndPoint> saveEndPoint(Application application, String path, String method, int rateLimit, int duration){
        if(getEndPoint(application, path, method).isPresent()){
            return Optional.empty();
        }
        EndPoint endPoint = new EndPoint(path, method, rateLimit, duration);
        endPoint.setApplication(application);
        return Optional.of(repository.save(endPoint));
    }

    public Long deleteEndPoint(Application application, String path, String method){
        return repository.deleteEndPointByApplicationAndPathAndMethod(application, path, method);
    }

    public Optional<EndPoint> getEndPoint(Application application, String path, String method){
        return repository.findByApplicationAndPathAndMethod(application, path, method);
    }
}
