package com.alby.emailservice.util;

import com.alby.emailservice.dto.request.EmailSendRequest;
import com.alby.emailservice.entity.Email;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
public class EmailUtil {

    public static Email mapAddRequestToFeedback(EmailSendRequest request) {
        return Email.builder()
                .sendTo(request.getSendTo())
                .subjects(request.getSubject())
                .contents(request.getContents())
                .build();
    }
}
