package com.flickspick.client.chatgpt;

import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.flickspick.client.dto.ChatGPTQueryRequest;
import com.flickspick.client.dto.ChatGPTQueryResponse;
import com.flickspick.client.dto.ChatGPTRole;
import com.flickspick.client.dto.Message;
import com.flickspick.common.client.WebClientFactory;

import reactor.core.publisher.Mono;

@Service
public class ChatGPTClientImpl implements ChatGPTClient {
	@Value("${chatgpt.api-key}")
	private String apiKey;

	@Override
	public ChatGPTQueryResponse query() {
		WebClient webClient = WebClientFactory.generate("https://api.openai.com/v1/chat/completions",
				20000,
				15000L,
				15000L
		);
		ChatGPTQueryRequest chatGPTQueryRequest = new ChatGPTQueryRequest(
				"gpt-3.5-turbo",
				List.of(new Message(ChatGPTRole.SYSTEM.getValue(), "You are an OTT movie recommendation expert. You can recommend movies currently available on OTT services like Netflix and Watcha. Please specify the OTT service (e.g., Netflix) and the country (e.g., Korea) for the recommendation. Provide your recommendation in the following format: {\\\"title\\\":\\\"[영화제목]\\\", \\\"reason\\\":\\\"xxx\\\", \\\"plot\\\":\\\"xxx\\\", \\\"character\\\":\\\"[types 중 1개 선택]\\\"}. Please respond only in Korean."),
						new Message(ChatGPTRole.SYSTEM.getValue(), "types: ['방구석 액션전문가', '골방 봉준호', '영화평론가 이동진']"),
						// TODO: 파라미터로 값 변경
						new Message(ChatGPTRole.USER.getValue(), "Please recommend a movie on Netflix in Korea."),
						new Message(ChatGPTRole.SYSTEM.getValue(), "{\\\"title\\\":\\\"[무작위 영화 제목]\\\", \\\"reason\\\":\\\"[무작위 이유]\\\", \\\"plot\\\":\\\"[무작위 줄거리]\\\", \\\"type\\\":[types]}")
				)
		);

		Mono<ChatGPTQueryResponse> chatGPTQueryResponseMono = webClient.post()
				.header("Authorization", "Bearer " + apiKey)
				.body(Mono.just(chatGPTQueryRequest), ChatGPTQueryRequest.class)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(ChatGPTQueryResponse.class);

		return chatGPTQueryResponseMono.block();
	}
}
