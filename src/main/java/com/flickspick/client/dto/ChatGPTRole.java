package com.flickspick.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatGPTRole {
	SYSTEM("system"),
	USER("user"),
	ASSISTANT("assistant")
	;

	private String value;
}
