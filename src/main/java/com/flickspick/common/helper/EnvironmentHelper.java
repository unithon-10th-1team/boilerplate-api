package com.flickspick.common.helper;

import static com.flickspick.common.consts.Static.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@RequiredArgsConstructor
public class EnvironmentHelper {
    private final Environment environment;

    private final List<String> PROD_AND_DEV = List.of(PROD, DEV);

    public String getCurrentProfile() {
        if (isProdProfile()) return PROD;
        if (isDevProfile()) return DEV;
        return LOCAL;
    }

    public Boolean isProdProfile() {
        String[] activeProfiles = environment.getActiveProfiles();
        List<String> currentProfile = Arrays.stream(activeProfiles).collect(Collectors.toList());
        return currentProfile.contains(PROD);
    }

    public Boolean isDevProfile() {
        String[] activeProfiles = environment.getActiveProfiles();
        List<String> currentProfile = Arrays.stream(activeProfiles).collect(Collectors.toList());
        return currentProfile.contains(DEV);
    }

    public Boolean isProdAndDevProfile() {
        String[] activeProfiles = environment.getActiveProfiles();
        List<String> currentProfile = Arrays.stream(activeProfiles).collect(Collectors.toList());
        return CollectionUtils.containsAny(PROD_AND_DEV, currentProfile);
    }
}
