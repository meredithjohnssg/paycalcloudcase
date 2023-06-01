package com.cloudcase.paycalcloudcase.json;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(requiredProperties = {"salaryAmount", "taxYear"})
public class TaxCalculationRequest {

        @Schema(description = "Salary amount", example = "60050")
        private int salaryAmount;

        @Schema(description = "Tax year", example = "2020")
        private int taxYear;
}
