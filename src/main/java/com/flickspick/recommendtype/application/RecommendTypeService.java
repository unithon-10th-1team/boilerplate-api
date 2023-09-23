package com.flickspick.recommendtype.application;

import com.flickspick.exception.dto.ErrorType;
import com.flickspick.exception.rec.RecommendTypeNotFoundException;
import com.flickspick.recommendtype.domain.RecommendType;
import com.flickspick.recommendtype.infrastructure.RecommendTypeRepository;
import com.flickspick.recommendtype.model.RecTypeModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendTypeService {
    private final RecommendTypeRepository recommendTypeRepository;
    private Map<Long, RecTypeModel> recTypeModels;

    @Scheduled(fixedRate = 1000 * 60 * 5, initialDelayString = "0")
    public void refreshRecTypeModels() {
        log.info("refresh recTypeModels info start");
        recTypeModels = refresh();
        log.info("refresh recTypeModels info complete");
    }

    public RecTypeModel getRecTypeModel(Long recommendTypeId) {
        RecommendType recommendType = recommendTypeRepository.findById(recommendTypeId)
                .orElseThrow(() -> new RecommendTypeNotFoundException(ErrorType.RECOMMEND_TYPE_NOT_FOUND_ERROR));
        return RecTypeModel.toModel(recommendType);
    }

    public Map<Long, RecTypeModel> refresh() {
        return recommendTypeRepository.findAll()
                .stream()
                .map(RecTypeModel::toModel)
                .collect(Collectors.toMap(RecTypeModel::getId, Function.identity()));
    }

    public RecTypeModel get(Long id) {
        var rec = recTypeModels.get(id);

        if (rec == null) {
            throw new RecommendTypeNotFoundException(ErrorType.RECOMMEND_TYPE_NOT_FOUND_ERROR);
        }

        return rec;
    }
}
