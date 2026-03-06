package com.inditex.marketdata.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Domain model representing a Price.
 *
 * @param id        The unique identifier of the price record.
 * @param brandId   The ID of the brand.
 * @param startDate The start date of the price validity.
 * @param endDate   The end date of the price validity.
 * @param priceList The price list identifier.
 * @param productId The ID of the product.
 * @param priority  The priority of the price.
 * @param price     The price value.
 * @param curr      The currency code.
 */
public record Price(
                Long id,
                Integer brandId,
                LocalDateTime startDate,
                LocalDateTime endDate,
                Integer priceList,
                Long productId,
                Integer priority,
                BigDecimal price,
                String curr) {
}
