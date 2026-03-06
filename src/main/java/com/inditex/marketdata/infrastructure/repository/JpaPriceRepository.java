package com.inditex.marketdata.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Spring Data JPA repository for PriceEntity.
 * Handles database operations for the PRICES table.
 */
@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Finds the first price entity that matches the brand, product, and application
     * date,
     * ordered by priority in descending order.
     *
     * @param brandId            The ID of the brand.
     * @param productId          The ID of the product.
     * @param applicationDate    The start date for the range check.
     * @param applicationDateEnd The end date for the range check.
     * @return An Optional containing the matching PriceEntity, if any.
     */
    Optional<PriceEntity> findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Integer brandId, Long productId, LocalDateTime applicationDate, LocalDateTime applicationDateEnd);
}
