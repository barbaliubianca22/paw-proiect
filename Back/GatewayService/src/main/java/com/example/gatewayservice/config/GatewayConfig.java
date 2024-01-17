package com.example.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Repository;

@Configuration
@Repository
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r
                        .path("/auth/**")
                        .uri("http://auth-serv:8080"))
                .route("problem-service", r -> r
                        .path("/problems/**")
                        .filters(f -> f.filter(authFilter().apply(new AuthFilter.Config())))

                        .uri("http://problem-service:8080"))
                .build();
    }
    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }
}

