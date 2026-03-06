package com.inditex.marketdata.infrastructure.repository.mapper;

import com.inditex.marketdata.domain.model.Price;
import com.inditex.marketdata.infrastructure.repository.PriceEntity;

import java.util.Objects;

public final class PriceEntityMapper {

    private PriceEntityMapper() {
    }

    public static Price from(PriceEntity entity) {
        Objects.requireNonNull(entity, "PriceEntity must not be null");

        return new Price(
                entity.getId(),
                entity.getBrandId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriceList(),
                entity.getProductId(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurr());
    }
}
