package com.flickspick.ott.service;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.exception.dto.ErrorType;
import com.flickspick.exception.ott.OttNotFoundException;
import com.flickspick.ott.domain.Ott;
import com.flickspick.ott.domain.OttUser;
import com.flickspick.ott.dto.OttRequest;
import com.flickspick.ott.dto.OttResponse;
import com.flickspick.ott.dto.OttsResponse;
import com.flickspick.ott.infrastructure.OttRepository;
import com.flickspick.ott.infrastructure.OttUserRepository;
import com.flickspick.ott.model.OttModel;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OttService {
    private final OttRepository ottRepository;
    private final OttUserRepository ottUserRepository;
    private Map<Long, OttModel> otts;

    @Scheduled(fixedRate = 1000 * 60 * 5, initialDelayString = "0")
    public void refreshOtts() {
        log.info("refresh recTypeModels info start");
        otts = refresh();
        log.info("refresh recTypeModels info complete");
    }

    public Map<Long, OttModel> refresh() {
        return ottRepository.findAll().stream()
                .map(OttModel::toModel)
                .collect(Collectors.toMap(OttModel::getId, Function.identity()));
    }

    public OttModel get(Long id) {
        var ott = otts.get(id);

        if (ott == null) {
            throw new OttNotFoundException(ErrorType.OTT_NOT_FOUND_ERROR);
        }

        return ott;
    }

    public OttsResponse getAll() {
        var otts =
                ottRepository.findAll().stream()
                        .map(OttResponse::from)
                        .collect(Collectors.toList());

        return new OttsResponse(otts);
    }

    public void create(AuthUser user, OttRequest request) {
        ottRepository.findAllById(request.getIds()).stream()
                .filter(ott -> !ottUserRepository.existsByUidAndOttId(user.getId(), ott.getId()))
                .forEach(ott -> saveOttUser(user, ott));
    }

    @Transactional
    public OttUser saveOttUser(AuthUser user, Ott ott) {
        return ottUserRepository.save(
                OttUser.builder().uid(user.getId()).ottId(ott.getId()).build());
    }

    public List<OttUser> findAllByUid(Long uid) {
        return ottUserRepository.findAllByUid(uid);
    }

    public Ott findById(Long ottId) {
        return ottRepository
                .findById(ottId)
                .orElseThrow(() -> new OttNotFoundException(ErrorType.OTT_NOT_FOUND_ERROR));
    }
}
