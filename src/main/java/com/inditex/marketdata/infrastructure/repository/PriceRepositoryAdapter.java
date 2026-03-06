package com.inditex.marketdata.infrastructure.repository;

import com.inditex.marketdata.domain.model.Price;
import com.inditex.marketdata.domain.port.PriceRepositoryPort;
import com.inditex.marketdata.infrastructure.repository.mapper.PriceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final JpaPriceRepository jpaPriceRepository;

    @Override
    public Optional<Price> getApplicablePrice(LocalDateTime applicationDate, Long productId, Integer brandId) {
        return jpaPriceRepository
                .findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        brandId, productId, applicationDate, applicationDate)
                .map(PriceEntityMapper::from);
    }
}
