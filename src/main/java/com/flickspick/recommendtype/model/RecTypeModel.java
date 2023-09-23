package com.flickspick.recommendtype.model;

import com.flickspick.recommendtype.domain.RecommendType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecTypeModel {
    private Long id;
    private String type;
    private List<String> tags;
    private String imageUrl;

    public static RecTypeModel toModel(RecommendType recommendType) {
        return new RecTypeModel(
                recommendType.getId(),
                recommendType.getRecommendType(),
                recommendType.getTags(),
                recommendType.getImageUrl());
    }
}
