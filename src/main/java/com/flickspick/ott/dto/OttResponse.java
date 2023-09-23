package com.flickspick.ott.dto;

import com.flickspick.ott.domain.Ott;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OttResponse {
    private Long id;
    private String name;
    private String nameEng;
    private String description;
    private String imageUrl;

    public static OttResponse from(Ott ott) {
        return new OttResponse(
                ott.getId(),
                ott.getName(),
                ott.getNameEng(),
                ott.getDescription(),
                ott.getImageUrl()
        );
    }
}
