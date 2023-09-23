package com.flickspick.question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionsResponse {
    private List<QuestionResponse> questions;
}
