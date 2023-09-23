package com.flickspick.ott.presentation;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.ott.service.OttService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ott 관리")
@RestController
@RequestMapping(path = "/api/v1/ott", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OttController {
    private final OttService ottService;

    @Operation(summary = "Ott 정보 조회")
    @GetMapping
    public ResponseEntity<?> getAll(@Nullable AuthUser user) {
        var response = ottService.getAll();
        return ResponseEntity.ok(response);
    }
}
