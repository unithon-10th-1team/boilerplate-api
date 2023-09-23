package com.flickspick.ott.model;

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
}
