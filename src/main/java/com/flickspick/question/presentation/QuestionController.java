package com.flickspick.question.presentation;

import com.flickspick.common.model.dto.ResponseDto;
import com.flickspick.question.application.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "질문 관리")
@RestController
@RequestMapping(path = "/api/v1/questions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @Operation(summary = "질문 리스트 제공")
    @GetMapping
    public ResponseEntity<?> getAll() {
        var response = questionService.getQuestions();
        return ResponseDto.ok(response);
    }
}
