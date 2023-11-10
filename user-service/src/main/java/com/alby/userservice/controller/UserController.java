package com.alby.userservice.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alby.userservice.dto.request.UserAddRequest;
import com.alby.userservice.dto.request.UserDeleteRequest;
import com.alby.userservice.dto.request.UserGetRequest;
import com.alby.userservice.dto.request.UserPagingRequest;
import com.alby.userservice.dto.request.UserUpdateRequest;
import com.alby.userservice.dto.response.UserResponse;
import com.alby.userservice.dto.response.WebResponse;
import com.alby.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    
    private UserService userService;
    
    @GetMapping(
        path = "/users",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<UserResponse>> getAll(@RequestParam UserPagingRequest request) {
        return userService.getAll(request);
    }
    
    @GetMapping(
        path = "/users/find",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> get(@RequestParam UserGetRequest request) {
        return userService.get(request);
    }
    
    @PostMapping(
        path = "/users",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> add(@RequestBody UserAddRequest request) {
        return userService.add(request);
    }
    
    @PutMapping(
        path = "/users",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> update(@RequestBody UserUpdateRequest request) {
        return userService.update(request);
    }
    
    @DeleteMapping(
        path = "/users",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@RequestBody UserDeleteRequest request) {
        return userService.delete(request);
    }
}
