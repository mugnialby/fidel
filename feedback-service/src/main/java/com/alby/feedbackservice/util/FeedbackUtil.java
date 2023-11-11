package com.alby.feedbackservice.util;

import com.alby.feedbackservice.dto.request.FeedbackAddRequest;
import com.alby.feedbackservice.dto.response.FeedbackResponse;
import com.alby.feedbackservice.entity.Feedback;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
public class FeedbackUtil {

    public static FeedbackResponse mapFeedbackToFeedbackResponse(Feedback feedback) {
        return FeedbackResponse.builder()
                .feedbackId(feedback.getId())
                .toUserId(feedback.getToUserId())
                .contents(feedback.getContents())
                .build();
    }

    public static Feedback mapAddRequestToFeedback(FeedbackAddRequest request) {
        return Feedback.builder()
                .toUserId(request.getToUserId())
                .feedbackTypeId(request.getFeedbackTypeId())
                .contents(request.getContents())
                .createdBy(request.getCreatedBy())
                .build();
    }
}
