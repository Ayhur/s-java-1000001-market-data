package com.inditex.marketdata.domain.port;

import com.inditex.marketdata.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Port interface for price repository operations.
 * Defines the contract for persistence adapters in the domain layer.
 */
public interface PriceRepositoryPort {
    /**
     * Retrieves the applicable price for a given product and brand at a specific
     * date.
     *
     * @param applicationDate The date for which the price is requested.
     * @param productId       The ID of the product.
     * @param brandId         The ID of the brand.
     * @return An Optional containing the found Price, or empty if none found.
     */
    Optional<Price> getApplicablePrice(LocalDateTime applicationDate, Long productId, Integer brandId);
}
