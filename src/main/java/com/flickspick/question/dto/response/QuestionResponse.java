package com.flickspick.question.dto.response;

import com.flickspick.question.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String question;
    private List<QuestionAnswerResponse> answers;

    public static QuestionResponse of(Question question, List<QuestionAnswerResponse> answers) {
        return new QuestionResponse(
                question.getId(),
                question.getQuestion(),
                answers
        );
    }
}
