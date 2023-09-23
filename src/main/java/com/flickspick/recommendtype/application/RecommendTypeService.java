package com.flickspick.recommendtype.application;

import org.springframework.stereotype.Service;

import com.flickspick.exception.dto.ErrorType;
import com.flickspick.exception.rec.RecommendTypeNotFoundException;
import com.flickspick.recommendtype.domain.RecommendType;
import com.flickspick.recommendtype.infrastructure.RecommendTypeRepository;
import com.flickspick.recommendtype.model.RecTypeModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendTypeService {
	private final RecommendTypeRepository recommendTypeRepository;

	public RecTypeModel getRecTypeModel(Long recommendTypeId) {
		RecommendType recommendType = recommendTypeRepository.findById(recommendTypeId)
				.orElseThrow(() -> new RecommendTypeNotFoundException(ErrorType.RECOMMEND_TYPE_NOT_FOUND_ERROR));
		return RecTypeModel.toModel(recommendType);
	}
}
