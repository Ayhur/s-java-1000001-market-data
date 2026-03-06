package com.inditex.marketdata.infrastructure.controller.mapper;

import com.inditex.marketdata.application.dto.PriceResponse;
import com.inditex.marketdata.domain.model.Price;

import java.util.Objects;

/**
 * Utility class to map domain Price objects to PriceResponse DTOs.
 */
public final class PriceResponseMapper {

    /**
     * Private constructor to prevent instantiation.
     */
    private PriceResponseMapper() {
    }

    /**
     * Maps a Price domain object to a PriceResponse DTO.
     *
     * @param price The domain price object to map.
     * @return A PriceResponse DTO.
     * @throws NullPointerException if price is null.
     */
    public static PriceResponse from(Price price) {
        Objects.requireNonNull(price, "Price must not be null");

        return new PriceResponse(
                price.productId(),
                price.brandId(),
                price.priceList(),
                price.startDate(),
                price.endDate(),
                price.price(),
                price.curr());
    }
}
