package com.flickspick.home.presentation;

import com.flickspick.common.model.dto.ResponseDto;
import com.flickspick.home.application.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "홈")
@RestController
@RequestMapping(path = "/api/v1/home", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class HomController {
    private final HomeService homeService;

    /*@Operation(summary = "홈 데이터 제공")
    @GetMapping
    public ResponseEntity<?> getHome() {
        var response = homeService.getHome();
        return ResponseDto.ok(response);
    }*/
}
