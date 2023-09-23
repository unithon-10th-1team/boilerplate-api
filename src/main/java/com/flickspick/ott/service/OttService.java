package com.flickspick.ott.service;

import com.flickspick.ott.dto.OttResponse;
import com.flickspick.ott.dto.OttsResponse;
import com.flickspick.ott.infrastructure.OttRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OttService {
    private final OttRepository ottRepository;

    public OttsResponse getAll() {
        var otts =
                ottRepository.findAll().stream()
                        .map(OttResponse::from)
                        .collect(Collectors.toList());

        return new OttsResponse(otts);
    }
}
