package com.flickspick.question.application;

import com.flickspick.question.dto.response.QuestionAnswerResponse;
import com.flickspick.question.dto.response.QuestionResponse;
import com.flickspick.question.dto.response.QuestionsResponse;
import com.flickspick.question.repository.QuestionAnswerRepository;
import com.flickspick.question.repository.QuestionRepository;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private static final Integer LIMIT_COUNT = 5;

    public QuestionsResponse getQuestions() {
        var questions = questionRepository.findAll();
        var questionAnswer =
                questionAnswerRepository.findAll().stream()
                        .map(
                                q ->
                                        new QuestionAnswerResponse(
                                                q.getId(), q.getQuestionId(), q.getAnswer()))
                        .collect(
                                Collectors.toMap(
                                        QuestionAnswerResponse::getId, Function.identity()));

        Collections.shuffle(questions);

        var questionAndAnswerModel =
                questions.stream()
                        .limit(LIMIT_COUNT)
                        .map(
                                q ->
                                        QuestionResponse.of(
                                                q,
                                                q.getAnswers().stream()
                                                        .map(questionAnswer::get)
                                                        .collect(Collectors.toList())))
                        .collect(Collectors.toList());

        return new QuestionsResponse(questionAndAnswerModel);
    }
}
