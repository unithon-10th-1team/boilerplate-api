package com.flickspick.ott.model;

import com.flickspick.ott.domain.Ott;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OttModel {
    private Long id;
    private String name;
    private String nameEng;
    private String description;
    private String imageUrl;

    public static OttModel toModel(Ott ott) {
        return new OttModel(ott.getId(), ott.getName(), ott.getNameEng(), ott.getDescription(), ott.getImageUrl());
    }
}
