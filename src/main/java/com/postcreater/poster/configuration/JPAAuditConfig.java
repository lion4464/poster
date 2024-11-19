package com.postcreater.poster.configuration;

import com.postcreater.poster.generic.UserDetailsImpl;
import com.postcreater.poster.utils.SecurityContextHolderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@RequiredArgsConstructor
public class JPAAuditConfig {
    private final SecurityContextHolderUtils securityContextHolderUtils;
    @Bean
    public AuditorAware<UUID> auditorProvider() {
        return () -> {

                if(Objects.nonNull(securityContextHolderUtils.getCurrentUser())) {
                    UserDetailsImpl userDetails = securityContextHolderUtils.getCurrentUser();
                    return Optional.ofNullable(userDetails.getUserId());
                }
            return Optional.empty();
        };
    }
}
