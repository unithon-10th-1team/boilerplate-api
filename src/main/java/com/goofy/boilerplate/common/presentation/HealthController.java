package com.goofy.boilerplate.common.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/health-check")
@RequiredArgsConstructor
public class HealthController {
    private final Environment environment;

    @GetMapping
    public String health() {
        return "health Good~!" + Arrays.toString(environment.getActiveProfiles());
    }
}
