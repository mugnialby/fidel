package com.alby.userservice.serviceimpl;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alby.userservice.service.ValidationService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private Validator validator;

    @Override
    public void validate(Object request) {

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
        if (constraintViolations.size() != 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        
    }
    
}
