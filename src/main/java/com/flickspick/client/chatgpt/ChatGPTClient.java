package com.flickspick.client.chatgpt;

import com.flickspick.client.dto.ChatGPTQueryResponse;

public interface ChatGPTClient {
	ChatGPTQueryResponse query();
}
