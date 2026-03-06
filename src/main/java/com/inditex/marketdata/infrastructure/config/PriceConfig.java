package com.inditex.marketdata.infrastructure.config;

import com.inditex.marketdata.application.usecase.GetPriceUseCase;
import com.inditex.marketdata.application.usecase.GetPriceUseCaseImpl;
import com.inditex.marketdata.domain.port.PriceRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for price-related beans.
 * Defines the wiring of use cases and their dependencies.
 */
@Configuration
public class PriceConfig {

    /**
     * Creates the GetPriceUseCase bean.
     *
     * @param priceRepositoryPort The repository port dependency.
     * @return An instance of GetPriceUseCaseImpl.
     */
    @Bean
    public GetPriceUseCase getPriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        return new GetPriceUseCaseImpl(priceRepositoryPort);
    }
}
