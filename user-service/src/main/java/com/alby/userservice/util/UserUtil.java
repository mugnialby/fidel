package com.alby.userservice.util;

import com.alby.userservice.dto.request.UserAddRequest;
import com.alby.userservice.dto.response.UserResponse;
import com.alby.userservice.entity.Users;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class UserUtil {
    
    public UserResponse mapUserToUserResponse(Users user) {
        return UserResponse
            .builder()
            .id(user.getId())
            .username(user.getUsername())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .status(user.getStatus())
            .build();
    }

    public Users mapAddRequestToUsers(UserAddRequest request) {
        return Users.builder()
            .username(request.getUsername())
            .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .build();
    }
}
