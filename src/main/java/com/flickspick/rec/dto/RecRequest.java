package com.flickspick.rec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

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
