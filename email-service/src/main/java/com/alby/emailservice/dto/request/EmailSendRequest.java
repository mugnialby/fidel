package com.alby.emailservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailSendRequest {

    @NotBlank
    @Length(max = 64)
    private String sendTo;

    @NotBlank
    @Length(max = 256)
    private String subject;

    @NotBlank
    @Length(max = 4000)
    private String contents;

    @NotBlank
    @Length(max = 64)
    private String createdBy;
}
