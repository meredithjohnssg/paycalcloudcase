package com.cloudcase.paycalcloudcase.json;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxCalculationRequest {

    @Min(0)
    @Max(999999999)
    private int salaryAmount;

    private int taxYear;

}
