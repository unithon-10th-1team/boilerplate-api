package com.flickspick.recommendtype.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecTypeModel {
    private Long id;
    private String type;
    private List<String> tags;
    private String imageUrl;
}
