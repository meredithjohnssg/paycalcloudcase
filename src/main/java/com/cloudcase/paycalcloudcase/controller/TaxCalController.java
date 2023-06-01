package com.cloudcase.paycalcloudcase.controller;

import com.cloudcase.paycalcloudcase.json.TaxCalculationRequest;
import com.cloudcase.paycalcloudcase.json.TaxCalculationResponse;
import com.cloudcase.paycalcloudcase.services.TaxCalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = TaxCalController.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "System error occurred", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
})
public class TaxCalController {

    public static final String BASE_URL = "/taxcal";

    @Autowired
    private TaxCalculationService taxCalculationService;


    @Operation(summary = "Get correct tax amount for the given salary")
    @PostMapping
    public TaxCalculationResponse getTaxAndSuperAmount(@RequestBody TaxCalculationRequest taxCalculationResquest) {
        return taxCalculationService.getTaxAndSuperAmount(taxCalculationResquest);
    }

}
