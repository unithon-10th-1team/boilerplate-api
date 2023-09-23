package com.flickspick.client.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseContent {
	private String title;
	private String reason;
	private String plot;
	private String recommend_type;
}
