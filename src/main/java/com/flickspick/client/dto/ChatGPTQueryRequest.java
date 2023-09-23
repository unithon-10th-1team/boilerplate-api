package com.flickspick.client.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatGPTQueryRequest {
    private String model;
    private List<RequestMessage> responseMessages;
}
