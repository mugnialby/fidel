package com.alby.feedbackservice.serviceimpl;

import com.alby.feedbackservice.dto.request.FeedbackAddRequest;
import com.alby.feedbackservice.dto.request.FeedbackGetRequest;
import com.alby.feedbackservice.dto.request.FeedbackPagingRequest;
import com.alby.feedbackservice.dto.response.FeedbackResponse;
import com.alby.feedbackservice.dto.response.WebResponse;
import com.alby.feedbackservice.entity.Feedback;
import com.alby.feedbackservice.repository.FeedbackRepository;
import com.alby.feedbackservice.service.FeedbackService;
import com.alby.feedbackservice.service.ValidationService;
import com.alby.feedbackservice.util.FeedbackUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    private final ValidationService validationService;

    @Override
    public WebResponse<List<FeedbackResponse>> getAll(FeedbackPagingRequest request) {
        validationService.validate(request);

        Page<Feedback> feedbacks = feedbackRepository.findAll(
                PageRequest.of(
                        request.getPage(),
                        request.getPageSize(),
                        Sort.by("id")
                                .ascending()
                )
        );

        List<FeedbackResponse> feedbackResponses = feedbacks
                .stream()
                .map(FeedbackUtil::mapFeedbackToFeedbackResponse)
                .collect(Collectors.toList());

        return WebResponse.<List<FeedbackResponse>> builder()
                .message("OK")
                .data(feedbackResponses)
                .build();
    }

    @Override
    public WebResponse<FeedbackResponse> get(FeedbackGetRequest request) {
        validationService.validate(request);

        return WebResponse.<FeedbackResponse> builder()
                .message("OK")
                .data(feedbackRepository.findById(request.getFeedbackId())
                        .map(FeedbackUtil::mapFeedbackToFeedbackResponse)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .build();
    }

    @Override
    public WebResponse<String> add(FeedbackAddRequest request) {
        validationService.validate(request);

        Feedback feedback = FeedbackUtil.mapAddRequestToFeedback(request);
        feedbackRepository.save(feedback);

        return WebResponse.<String> builder()
                .data("OK")
                .build();
    }
}
