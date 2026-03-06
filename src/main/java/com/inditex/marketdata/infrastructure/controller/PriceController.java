package com.inditex.marketdata.infrastructure.controller;

import com.inditex.marketdata.application.dto.PriceResponse;
import com.inditex.marketdata.application.usecase.GetPriceUseCase;
import com.inditex.marketdata.infrastructure.controller.mapper.PriceResponseMapper;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * REST controller for managing price inquiries.
 * provides an endpoint to query the applicable price for a product, brand, and
 * date.
 */
@Tag(name = "Prices", description = "Operations related to product prices")
@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceController {

    private final GetPriceUseCase getPriceUseCase;

    /**
     * Endpoint to retrieve the applicable price for a given product, brand, and
     * date.
     *
     * @param applicationDate The date for which the price is requested.
     * @param productId       The ID of the product.
     * @param brandId         The ID of the brand.
     * @return A ResponseEntity containing the PriceResponse if found, or 404 Not
     *         Found otherwise.
     */
    @Operation(summary = "Get applicable price", description = "Retrieves the highest priority price for a product and brand at a specific date.")
    @ApiResponse(responseCode = "200", description = "Price found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PriceResponse.class)))
    @ApiResponse(responseCode = "404", description = "Price not found", content = @Content)
    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @Parameter(description = "Date of application (ISO format)", example = "2020-06-14T16:00:00", required = true) @RequestParam(name = "applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @Parameter(description = "Identifier of the product", example = "35455", required = true) @RequestParam(name = "productId") Long productId,
            @Parameter(description = "Identifier of the brand", example = "1", required = true) @RequestParam(name = "brandId") Integer brandId) {

        var query = new GetPriceUseCase.GetPriceQuery(applicationDate, productId, brandId);
        return getPriceUseCase.execute(query)
                .map(PriceResponseMapper::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
