package com.inditex.marketdata.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
