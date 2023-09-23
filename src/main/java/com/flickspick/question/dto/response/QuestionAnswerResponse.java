package com.flickspick.question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionAnswerResponse {
    private Long id;
    private Long questionId;
    private String answer;
}
