package com.alby.userservice.service;

import java.util.List;

import com.alby.userservice.dto.request.UserAddRequest;
import com.alby.userservice.dto.request.UserDeleteRequest;
import com.alby.userservice.dto.request.UserGetRequest;
import com.alby.userservice.dto.request.UserPagingRequest;
import com.alby.userservice.dto.request.UserUpdateRequest;
import com.alby.userservice.dto.response.UserResponse;
import com.alby.userservice.dto.response.WebResponse;

public interface UserService {
    
    WebResponse<List<UserResponse>> getAll(UserPagingRequest request);

    WebResponse<UserResponse> get(UserGetRequest request);

    WebResponse<UserResponse> add(UserAddRequest request);

    WebResponse<UserResponse> update(UserUpdateRequest request);

    WebResponse<String> delete(UserDeleteRequest request);
}
