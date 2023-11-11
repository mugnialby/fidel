package com.alby.emailservice.serviceimpl;

import com.alby.emailservice.dto.request.EmailSendRequest;
import com.alby.emailservice.dto.response.WebResponse;
import com.alby.emailservice.entity.Email;
import com.alby.emailservice.repository.EmailRepository;
import com.alby.emailservice.service.EmailService;
import com.alby.emailservice.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    @Override
    public WebResponse<String> send(EmailSendRequest request) {
        Email email = EmailUtil.mapAddRequestToFeedback(request);
        emailRepository.save(email);

        return WebResponse.<String> builder()
                .data("OK")
                .build();
    }
}
