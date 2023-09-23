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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OttService {
    private final OttRepository ottRepository;
    private final OttUserRepository ottUserRepository;

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
                OttUser.builder()
                        .uid(user.getId())
                        .ottId(ott.getId())
                        .build()
        );
    }

    public List<OttUser> findAllByUid(Long uid) {
        return ottUserRepository.findAllByUid(uid);
    }

    public Ott findById(Long ottId) {
        return ottRepository.findById(ottId)
                .orElseThrow(() -> new OttNotFoundException(ErrorType.OTT_NOT_FOUND_ERROR));
    }
}
