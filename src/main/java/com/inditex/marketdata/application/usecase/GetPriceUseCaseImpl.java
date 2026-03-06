package com.inditex.marketdata.application.usecase;

import com.inditex.marketdata.domain.model.Price;
import com.inditex.marketdata.domain.port.PriceRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * Implementation of the GetPriceUseCase interface.
 * Coordinates the retrieval of price information from the domain port.
 */
@RequiredArgsConstructor
public class GetPriceUseCaseImpl implements GetPriceUseCase {

    /** The repository port for price data access */
    private final PriceRepositoryPort priceRepositoryPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Price> execute(GetPriceQuery query) {
        return priceRepositoryPort.getApplicablePrice(query.applicationDate(), query.productId(), query.brandId());
    }
}
