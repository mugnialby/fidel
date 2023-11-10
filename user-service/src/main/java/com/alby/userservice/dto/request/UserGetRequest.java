package com.alby.userservice.dto.request;

import com.alby.userservice.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGetRequest {
    
    @NonNull
    private Long userId;

    @NonNull
    private Users currentUser;
}
