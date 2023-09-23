package com.flickspick.batch;

import com.flickspick.client.chatgpt.ChatGPTClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "배치 서비스")
@RestController
@RequestMapping(path = "/api/v1/batch", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BatchController {
    private final BatchService batchService;
    private final ChatGPTClient chatGPTClient;

    @PostMapping
    public void batch() {
        batchService.batch();
    }
}
