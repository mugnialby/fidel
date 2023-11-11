package com.alby.feedbackservice.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackAddRequest {

    @NotNull
    @Digits(integer = 12, fraction = 0)
    private Long toUserId;

    @NotNull
    @Digits(integer = 3, fraction = 0)
    private int feedbackTypeId;

    @NotBlank
    @Length(max = 1048)
    private String contents;

    @NotBlank
    @Length(max = 64)
    private String createdBy;
}
