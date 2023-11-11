package com.alby.userservice.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddRequest {
    
    @NotBlank
    @Length(max = 64)
    private String username;

    @NotBlank
    @Length(max = 128)
    private String password;

    @NotBlank
    @Length(max = 128)
    private String firstName;

    @NotBlank
    @Length(max = 128)
    private String lastName;

    @NotBlank
    @Length(max = 64)
    private String email;

    @NotNull
    @Digits(integer = 12, fraction = 0)
    private Long managerId;

    @NotBlank
    @Length(max = 64)
    private String createdBy;
}
