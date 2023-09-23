package com.flickspick.rec.presentation;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.common.model.dto.ResponseDto;
import com.flickspick.rec.application.RecService;
import com.flickspick.rec.dto.RecRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "추천 데이터 관리")
@RestController
@RequestMapping(path = "/api/v1/rec", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RecController {
    private final RecService recService;

    @Operation(summary = "질의 기반 추천 데이터 조회")
    @PostMapping("/query")
    public ResponseEntity<?> query(AuthUser user, @RequestBody RecRequest request) {
        var response = recService.get(user, request);
        return ResponseDto.ok(response);
    }
}
