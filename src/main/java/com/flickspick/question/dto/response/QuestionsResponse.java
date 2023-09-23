package com.flickspick.question.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionsResponse {
    private List<QuestionResponse> questions;
}
