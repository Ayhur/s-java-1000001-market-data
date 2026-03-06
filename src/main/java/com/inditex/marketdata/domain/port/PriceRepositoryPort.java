package com.inditex.marketdata.domain.port;

import com.inditex.marketdata.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<Price> getApplicablePrice(LocalDateTime applicationDate, Long productId, Integer brandId);
}
