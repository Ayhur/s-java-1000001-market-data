package com.inditex.marketdata.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI documentation.
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Market Data API", version = "1.0", description = "API for retrieving applicable product prices"))
public class OpenApiConfig {
}
