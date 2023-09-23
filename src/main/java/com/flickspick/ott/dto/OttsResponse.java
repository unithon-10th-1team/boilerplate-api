package com.flickspick.ott.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OttsResponse {
    private List<OttResponse> otts;
}
