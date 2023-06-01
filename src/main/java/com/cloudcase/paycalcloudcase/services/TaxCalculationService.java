package com.cloudcase.paycalcloudcase.services;

import com.cloudcase.paycalcloudcase.jpa.TaxBracket;
import com.cloudcase.paycalcloudcase.jpa.TaxBracketDataStore;
import com.cloudcase.paycalcloudcase.json.TaxCalculationRequest;
import com.cloudcase.paycalcloudcase.json.TaxCalculationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxCalculationService {

    @Autowired
    private TaxBracketDataStore taxBracketDataStore;

    public TaxCalculationResponse getTaxAndSuperAmount(TaxCalculationRequest taxCalculationRequest) {

        List<TaxBracket> taxBrackets = taxBracketDataStore.taxBracketRepository.findAll();

        TaxBracket taxBracketActual = taxBrackets.stream().filter(
                taxBracket ->
                    taxCalculationRequest.getSalaryAmount() >= taxBracket.getTaxableIncomeStart()
                        && taxCalculationRequest.getSalaryAmount() <= taxBracket.getTaxableIncomeEnd()
                            && taxCalculationRequest.getTaxYear() == taxBracket.getTaxYear()).findFirst().get();

        int taxAmount = taxBracketActual.getTaxBase() + (taxCalculationRequest.getSalaryAmount() * taxBracketActual.getTaxRate()) / 100;
        int superAnnuation = Math.toIntExact(Math.round((taxCalculationRequest.getSalaryAmount() * 10.5) / 100));

        return TaxCalculationResponse.builder()
                .annualTax(taxAmount)
                .annualSuper(superAnnuation)
                .taxYearGiven(taxCalculationRequest.getTaxYear())
                .salaryAmountGiven(taxCalculationRequest.getSalaryAmount())
                .taxBase(taxBracketActual.getTaxBase())
                .taxRate(taxBracketActual.getTaxRate() + "%")
                .build();
    }
}
