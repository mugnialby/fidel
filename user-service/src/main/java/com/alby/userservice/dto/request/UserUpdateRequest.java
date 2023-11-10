package com.alby.userservice.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {
    
    @Size(max = 64)
    private String username;

    @Size(max = 128)
    private String password;

    @Size(max = 128)
    private String firstName;

    @Size(max = 128)
    private String lastName;

    @Size(max = 64)
    private String email;

    @Size(max = 1)
    private String status;
}
