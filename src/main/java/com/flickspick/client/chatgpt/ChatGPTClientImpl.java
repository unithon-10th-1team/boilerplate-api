package com.flickspick.client.chatgpt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.flickspick.client.dto.ChatGPTQueryRequest;
import com.flickspick.client.dto.ChatGPTQueryResponse;
import com.flickspick.client.dto.ChatGPTRole;
import com.flickspick.client.dto.RequestMessage;
import com.flickspick.client.dto.ResponseContent;
import com.flickspick.client.dto.ResponseMessage;
import com.flickspick.common.client.WebClientFactory;
import com.flickspick.ott.domain.Ott;
import com.flickspick.ott.infrastructure.OttRepository;
import com.flickspick.question.domain.Question;
import com.flickspick.question.domain.QuestionAnswer;
import com.flickspick.question.repository.QuestionAnswerRepository;
import com.flickspick.question.repository.QuestionRepository;
import com.flickspick.rec.dto.RecRequest.QuestionModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGPTClientImpl implements ChatGPTClient {
	@Value("${chatgpt.api-key}")
	private String apiKey;

	private final OttRepository ottRepository;
	private final QuestionRepository questionRepository;
	private final QuestionAnswerRepository questionAnswerRepository;

	@Override
	public ResponseMessage query(List<QuestionModel> answers, List<Long> ottIds) {
		WebClient webClient = WebClientFactory.generate("https://api.openai.com/v1/chat/completions",
				20000,
				15000L,
				15000L
		);
		List<RequestMessage> requestMessageList = new ArrayList<>();
		StringBuilder ottNames = new StringBuilder();
		for (Long ottId: ottIds) {
			Ott ott = ottRepository.findById(ottId)
					.orElseThrow(() -> new IllegalArgumentException("찾는 OTT가 없습니다."));
			if (ottNames.length() > 0) {
				ottNames.append(" And ");
			}
			ottNames.append(ott.getNameEng());
		}
		requestMessageList.add(new RequestMessage(ChatGPTRole.SYSTEM.getValue(), "You are an OTT movie recommendation expert. You can recommend movies currently available on OTT services like" + ottNames + ". Please specify the OTT service (e.g., Netflix) and the country (e.g., Korea) for the recommendation. Provide your recommendation in the following format: {\\\"title\\\":\\\"xxx\\\", \\\"reason\\\":\\\"xxx\\\", \\\"plot\\\":\\\"xxx\\\", \\\"recommend_type\\\":\\\"[types 중 1개 선택]\\\"}. Please respond only in Korean."));
		requestMessageList.add(new RequestMessage(ChatGPTRole.SYSTEM.getValue(), "types: ['코난 꿈나무', '방구석 액션전문가', '4계절 내내 여름 공포중독자', '극 I, 평화주의자', '인간 수도꼭지', '영화계의 설민석', '이별중이신가요?', '벚꽃이 보이시나요?']"));

		for (QuestionModel answer: answers) {
			Question question = questionRepository.findById(answer.getQuestionId())
					.orElseThrow(() -> new IllegalArgumentException("찾는 질문이 없습니다."));
			requestMessageList.add(new RequestMessage(ChatGPTRole.USER.getValue(), question.getQuestion()));
			QuestionAnswer questionAnswer = questionAnswerRepository.findById(answer.getAnswerId())
					.orElseThrow(() -> new IllegalArgumentException("찾는 질문이 없습니다."));
			requestMessageList.add(new RequestMessage(ChatGPTRole.ASSISTANT.getValue(), questionAnswer.getAnswer()));
		}

		ChatGPTQueryRequest chatGPTQueryRequest = new ChatGPTQueryRequest(
				"gpt-3.5-turbo",
				requestMessageList
		);

		ChatGPTQueryResponse queryResponse = webClient.post()
				.header("Authorization", "Bearer " + apiKey)
				.body(Mono.just(chatGPTQueryRequest), ChatGPTQueryRequest.class)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(ChatGPTQueryResponse.class).block();
		return queryResponse.getChoices().get(0).getMessage();
	}
}
