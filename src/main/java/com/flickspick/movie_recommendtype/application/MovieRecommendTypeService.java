package com.flickspick.movie_recommendtype.application;

import org.springframework.stereotype.Service;

import com.flickspick.movie_recommendtype.infrastructure.MovieRecommendTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieRecommendTypeService {
	private final MovieRecommendTypeRepository movieRecommendTypeRepository;


}
