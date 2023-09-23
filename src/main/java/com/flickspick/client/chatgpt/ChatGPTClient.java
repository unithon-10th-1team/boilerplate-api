package com.flickspick.client.chatgpt;

import com.flickspick.client.dto.ResponseMessage;
import com.flickspick.rec.dto.RecRequest;
import java.util.List;

public interface ChatGPTClient {
    ResponseMessage query(List<RecRequest.QuestionModel> answers, List<Long> ottIds);
}
