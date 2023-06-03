package com.cloudcase.paycalcloudcase.json;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaxCalculationResponse {

    private int taxYearGiven;
    private int salaryAmountGiven;
    private String taxRate;
    private int taxBase;
    private int annualTax;
    private int annualSuper;
}
