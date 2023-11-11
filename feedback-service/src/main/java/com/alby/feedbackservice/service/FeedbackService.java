package com.alby.feedbackservice.service;

import com.alby.feedbackservice.dto.request.FeedbackAddRequest;
import com.alby.feedbackservice.dto.request.FeedbackGetRequest;
import com.alby.feedbackservice.dto.request.FeedbackPagingRequest;
import com.alby.feedbackservice.dto.response.FeedbackResponse;
import com.alby.feedbackservice.dto.response.WebResponse;

import java.util.List;

public interface FeedbackService {

    WebResponse<List<FeedbackResponse>> getAll(FeedbackPagingRequest request);

    WebResponse<FeedbackResponse> get(FeedbackGetRequest request);

    WebResponse<String> add(FeedbackAddRequest request);
}
