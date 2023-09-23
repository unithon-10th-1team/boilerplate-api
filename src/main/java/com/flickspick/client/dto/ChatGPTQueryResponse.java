package com.flickspick.client.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTQueryResponse {
    private String id;
    private String object;
    private Long created;
    private String model;
    private List<ChatGPTChoice> choices;
    private ChatGPTUsage usage;
}
