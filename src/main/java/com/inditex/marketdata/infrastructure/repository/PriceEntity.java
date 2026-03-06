package com.inditex.marketdata.infrastructure.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Persistence entity representing the PRICES table.
 */
@Entity
@Table(name = "PRICES")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PriceEntity {

    /** Unique identifier for the price record */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Foreign key for the brand (group company) */
    @Column(name = "BRAND_ID")
    private Integer brandId;

    /** Start date from which the price is applicable */
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    /** End date until which the price is applicable */
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    /** Identifier for the applicable rate list */
    @Column(name = "PRICE_LIST")
    private Integer priceList;

    /** Unique identifier for the product */
    @Column(name = "PRODUCT_ID")
    private Long productId;

    /** Price application priority (higher value takes precedence) */
    @Column(name = "PRIORITY")
    private Integer priority;

    /** Final sale price */
    @Column(name = "PRICE")
    private BigDecimal price;

    /** ISO currency code */
    @Column(name = "CURR")
    private String curr;
}
