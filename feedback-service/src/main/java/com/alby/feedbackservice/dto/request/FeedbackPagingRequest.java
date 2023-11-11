package com.alby.feedbackservice.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackPagingRequest {

    @NotNull
    @Digits(integer = 6, fraction = 0)
    private Integer page;

    @NotNull
    @Digits(integer = 6, fraction = 0)
    private Integer pageSize;
}
