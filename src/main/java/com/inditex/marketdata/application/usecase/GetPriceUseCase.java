package com.inditex.marketdata.application.usecase;

import com.inditex.marketdata.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

import java.util.Objects;

/**
 * Use case interface for retrieving price information.
 */
public interface GetPriceUseCase {
    /**
     * Query object containing the parameters needed to find an applicable price.
     *
     * @param applicationDate The date for which the price is requested.
     * @param productId       The ID of the product.
     * @param brandId         The ID of the brand (company).
     */
    record GetPriceQuery(LocalDateTime applicationDate, Long productId, Integer brandId) {
        /**
         * constructor for GetPriceQuery that validates non-null parameters.
         */
        public GetPriceQuery {
            Objects.requireNonNull(applicationDate, "applicationDate must not be null");
            Objects.requireNonNull(productId, "productId must not be null");
            Objects.requireNonNull(brandId, "brandId must not be null");
        }
    }

    /**
     * Executes the use case to find the applicable price for the given query.
     *
     * @param query The query parameters.
     * @return An Optional containing the found Price, or empty if none found.
     */
    Optional<Price> execute(GetPriceQuery query);
}
