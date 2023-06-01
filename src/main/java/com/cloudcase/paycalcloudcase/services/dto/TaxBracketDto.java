package com.cloudcase.paycalcloudcase.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
@Getter
@Setter
public class TaxBracketDto {
    private int taxableIncomeStart;
    private int taxableIncomeEnd;
    private int taxBase;
    private int taxRate;
}
