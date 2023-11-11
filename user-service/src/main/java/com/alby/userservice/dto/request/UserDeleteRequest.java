package com.alby.userservice.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
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
public class UserDeleteRequest {
    
    @NotNull
    @Digits(integer = 12, fraction = 0)
    private Long userId;

    @NotNull
    @Length(max = 64)
    private String modifiedBy;
}
