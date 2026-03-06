package com.inditex.marketdata.infrastructure.controller.mapper;

import com.inditex.marketdata.application.dto.PriceResponse;
import com.inditex.marketdata.domain.model.Price;

import java.util.Objects;

public final class PriceResponseMapper {

    private PriceResponseMapper() {
    }

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
