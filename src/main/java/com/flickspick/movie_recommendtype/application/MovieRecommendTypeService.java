package com.flickspick.movie_recommendtype.application;

import com.flickspick.movie_recommendtype.infrastructure.MovieRecommendTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieRecommendTypeService {
    private final MovieRecommendTypeRepository movieRecommendTypeRepository;
}
