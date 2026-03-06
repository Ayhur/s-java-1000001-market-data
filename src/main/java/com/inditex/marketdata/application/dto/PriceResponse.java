package com.inditex.marketdata.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object representing a price response.
 *
 * @param productId The ID of the product.
 * @param brandId   The ID of the brand.
 * @param priceList The price list identifier.
 * @param startDate The start date of the price validity.
 * @param endDate   The end date of the price validity.
 * @param price     The price value.
 * @param curr      The currency code.
 */
@Schema(description = "Price details for a product")
public record PriceResponse(
        @Schema(description = "Product ID", example = "35455") Long productId,
        @Schema(description = "Brand ID", example = "1") Integer brandId,
        @Schema(description = "Price list identifier", example = "1") Integer priceList,
        @Schema(description = "Start date of price application") LocalDateTime startDate,
        @Schema(description = "End date of price application") LocalDateTime endDate,
        @Schema(description = "Price value", example = "35.50") BigDecimal price,
        @Schema(description = "Currency code", example = "EUR") String curr) {
}
