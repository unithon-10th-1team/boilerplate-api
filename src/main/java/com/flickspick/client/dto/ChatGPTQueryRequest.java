package com.flickspick.client.dto;

import java.util.List;

import com.flickspick.ott.domain.Ott;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatGPTQueryRequest {
    private String model;
    private List<Message> messages;
}
