package com.alby.feedbackservice.controller;

import com.alby.feedbackservice.dto.request.FeedbackAddRequest;
import com.alby.feedbackservice.dto.request.FeedbackGetRequest;
import com.alby.feedbackservice.dto.request.FeedbackPagingRequest;
import com.alby.feedbackservice.dto.response.FeedbackResponse;
import com.alby.feedbackservice.dto.response.WebResponse;
import com.alby.feedbackservice.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<FeedbackResponse>> getAll(@ModelAttribute FeedbackPagingRequest request) {
        return feedbackService.getAll(request);
    }

    @GetMapping(
            path = "/find",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<FeedbackResponse> get(@ModelAttribute FeedbackGetRequest request) {
        return feedbackService.get(request);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> add(@RequestBody FeedbackAddRequest request) {
        return feedbackService.add(request);
    }
}
