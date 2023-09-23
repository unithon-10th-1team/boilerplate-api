package com.flickspick.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTChoice {
    private Integer index;
    private ResponseMessage message;
    private String finish_reason;
}
