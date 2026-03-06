package com.inditex.marketdata.application.usecase;

import com.inditex.marketdata.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

import java.util.Objects;

public interface GetPriceUseCase {
    record GetPriceQuery(LocalDateTime applicationDate, Long productId, Integer brandId) {
        public GetPriceQuery {
            Objects.requireNonNull(applicationDate, "applicationDate must not be null");
            Objects.requireNonNull(productId, "productId must not be null");
            Objects.requireNonNull(brandId, "brandId must not be null");
        }
    }

    Optional<Price> execute(GetPriceQuery query);
}
