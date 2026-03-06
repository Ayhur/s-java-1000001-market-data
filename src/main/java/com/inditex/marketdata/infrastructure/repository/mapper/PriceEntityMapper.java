package com.inditex.marketdata.infrastructure.repository.mapper;

import com.inditex.marketdata.domain.model.Price;
import com.inditex.marketdata.infrastructure.repository.PriceEntity;

import java.util.Objects;

/**
 * Utility class to map PriceEntity objects to domain Price objects.
 */
public final class PriceEntityMapper {

    /**
     * Private constructor to prevent instantiation.
     */
    private PriceEntityMapper() {
    }

    /**
     * Maps a PriceEntity to a domain Price object.
     *
     * @param entity The persistence entity to map.
     * @return A domain Price object.
     * @throws NullPointerException if entity is null.
     */
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
