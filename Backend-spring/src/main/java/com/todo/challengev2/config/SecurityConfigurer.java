package com.todo.challengev2.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfigurer {

    private final RestServiceConfiguration restServiceConfiguration;

    @Bean
    public SecurityFilterChain configureHttpSecurity(final HttpSecurity http) throws Exception {
        // disable CSRF (makes no sense in a REST context) protection and add CORS allowances
        http.csrf().disable().cors().configurationSource(corsConfiguration());

        // build the filter chain
        return http.build();
    }

    /**
     * Builds the CORS configuration required for local testing with an Angular Dev
     * Server.
     *
     * @return the CORS configuration
     */
    private CorsConfigurationSource corsConfiguration() {
        final CorsConfiguration configuration = new CorsConfiguration();
        if (restServiceConfiguration.isCorsEnabled()) {
            // allow GET, PUT, POST, PATCH, DELETE
            configuration.setAllowedMethods(List.of(HttpMethod.GET.name(), HttpMethod.PUT.name(),
                    HttpMethod.POST.name(), HttpMethod.PATCH.name(), HttpMethod.DELETE.name()));
            // allow origin http://localhost:4200
            configuration.setAllowedOrigins(restServiceConfiguration.getCorsAllowedOrigins());
            // allow all headers
            configuration.setAllowedHeaders(restServiceConfiguration.getCorsAllowedHeaders());
            log.info("CORS enabled");
        }

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
