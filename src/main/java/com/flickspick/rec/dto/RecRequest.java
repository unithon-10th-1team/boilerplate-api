package com.flickspick.rec.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecRequest {
    private List<QuestionModel> answers;
    private List<Long> ottIds;

    @Data
    @AllArgsConstructor
    public static class QuestionModel {
        private Long questionId;
        private Long answerId;
    }
}
