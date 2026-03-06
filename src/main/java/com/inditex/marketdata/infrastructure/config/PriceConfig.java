package com.inditex.marketdata.infrastructure.config;

import com.inditex.marketdata.application.usecase.GetPriceUseCase;
import com.inditex.marketdata.application.usecase.GetPriceUseCaseImpl;
import com.inditex.marketdata.domain.port.PriceRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceConfig {

    @Bean
    public GetPriceUseCase getPriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        return new GetPriceUseCaseImpl(priceRepositoryPort);
    }
}
