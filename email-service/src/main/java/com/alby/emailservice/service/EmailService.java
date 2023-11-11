package com.alby.emailservice.service;

import com.alby.emailservice.dto.request.EmailSendRequest;
import com.alby.emailservice.dto.response.WebResponse;

public interface EmailService {
    WebResponse<String> send(EmailSendRequest request);

}
