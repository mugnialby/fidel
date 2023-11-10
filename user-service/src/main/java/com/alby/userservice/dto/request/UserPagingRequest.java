package com.alby.userservice.dto.request;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPagingRequest {
    
    @Nonnull
    private Integer page;

    @Nonnull
    private Integer pageSize;
}
