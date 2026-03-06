package com.inditex.marketdata.application.usecase;

import com.inditex.marketdata.domain.model.Price;
import com.inditex.marketdata.domain.port.PriceRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GetPriceUseCaseImpl implements GetPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    @Override
    public Optional<Price> execute(GetPriceQuery query) {
        return priceRepositoryPort.getApplicablePrice(query.applicationDate(), query.productId(), query.brandId());
    }
}
