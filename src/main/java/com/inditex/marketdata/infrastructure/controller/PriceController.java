package com.inditex.marketdata.infrastructure.controller;

import com.inditex.marketdata.application.dto.PriceResponse;
import com.inditex.marketdata.application.usecase.GetPriceUseCase;
import com.inditex.marketdata.infrastructure.controller.mapper.PriceResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceController {

    private final GetPriceUseCase getPriceUseCase;

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam(name = "applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "brandId") Integer brandId) {

        var query = new GetPriceUseCase.GetPriceQuery(applicationDate, productId, brandId);
        return getPriceUseCase.execute(query)
                .map(PriceResponseMapper::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
