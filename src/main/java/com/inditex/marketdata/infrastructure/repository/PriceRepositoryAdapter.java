package com.inditex.marketdata.infrastructure.repository;

import com.inditex.marketdata.domain.model.Price;
import com.inditex.marketdata.domain.port.PriceRepositoryPort;
import com.inditex.marketdata.infrastructure.repository.mapper.PriceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Infrastructure adapter for the PriceRepositoryPort.
 * Implements domain port using Spring Data JPA repository.
 */
@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    /** The JPA repository for price data access */
    private final JpaPriceRepository jpaPriceRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Price> getApplicablePrice(LocalDateTime applicationDate, Long productId, Integer brandId) {
        return jpaPriceRepository
                .findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        brandId, productId, applicationDate, applicationDate)
                .map(PriceEntityMapper::from);
    }
}
