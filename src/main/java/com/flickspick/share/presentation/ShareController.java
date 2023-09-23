package com.flickspick.share.presentation;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.common.model.dto.ResponseDto;
import com.flickspick.share.application.ShareService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공유")
@RestController
@RequestMapping(path = "/api/v1/share", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ShareController {
    private final ShareService shareService;

    @Operation(summary = "공유하기")
    @GetMapping
    public ResponseEntity<?> share(AuthUser authUser) {
        var response = shareService.share(authUser);
        return ResponseDto.ok(response);
    }
}
