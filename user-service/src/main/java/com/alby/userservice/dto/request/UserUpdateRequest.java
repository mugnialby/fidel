package com.alby.userservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {

    @NotNull
    @Digits(integer = 12, fraction = 0)
    private Long userId;
    
    @Length(max = 64)
    private String username;

    @Length(max = 128)
    private String password;

    @Length(max = 128)
    private String firstName;

    @Length(max = 128)
    private String lastName;

    @Length(max = 64)
    private String email;

    @Length(max = 1)
    private String status;

    @NotNull
    @Digits(integer = 12, fraction = 0)
    private Long managerId;

    @NotBlank
    @Length(max = 64)
    private String modifiedBy;
}
