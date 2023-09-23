package com.flickspick.ott.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OttsResponse {
    private List<OttResponse> otts;
}
