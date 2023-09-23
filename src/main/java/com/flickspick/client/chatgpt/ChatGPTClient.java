package com.flickspick.client.chatgpt;

import java.util.List;

import com.flickspick.client.dto.ResponseMessage;
import com.flickspick.rec.dto.RecRequest;

public interface ChatGPTClient {
	ResponseMessage query(List<RecRequest.QuestionModel> answers, List<Long> ottIds);
}
