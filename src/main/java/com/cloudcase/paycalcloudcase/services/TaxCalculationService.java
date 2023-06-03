package com.cloudcase.paycalcloudcase.services;

import com.cloudcase.paycalcloudcase.jpa.TaxBracket;
import com.cloudcase.paycalcloudcase.jpa.TaxBracketDataStore;
import com.cloudcase.paycalcloudcase.json.TaxCalculationRequest;
import com.cloudcase.paycalcloudcase.json.TaxCalculationResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Setter
public class TaxCalculationService {

    @Autowired
    private TaxBracketDataStore taxBracketDataStore;

    /**
     * This method is used to calculate tax and super amount
     *
     * @param taxCalculationRequest
     * @return
     * @throws RuntimeException
     */
    public TaxCalculationResponse getTaxAndSuperAmount(TaxCalculationRequest taxCalculationRequest) throws RuntimeException {

        List<TaxBracket> taxBrackets = taxBracketDataStore.findAll();

        Optional<TaxBracket> taxBracketActual = taxBrackets.stream().filter(
                taxBracket ->
                        taxCalculationRequest.getSalaryAmount() >= taxBracket.getTaxableIncomeStart()
                                && taxCalculationRequest.getSalaryAmount() <= taxBracket.getTaxableIncomeEnd()
                                && taxCalculationRequest.getTaxYear() == taxBracket.getTaxYear()).findFirst();

        if (!taxBracketActual.isPresent()) {
            throw new RuntimeException("No tax bracket found for the given salary amount and tax year");
        }

        TaxBracket taxBracket = taxBracketActual.get();
        int taxAmount = taxBracket.getTaxBase() + (taxCalculationRequest.getSalaryAmount() * taxBracket.getTaxRate()) / 100;
        int superAnnuation = Math.toIntExact(Math.round((taxCalculationRequest.getSalaryAmount() * 10.5) / 100)); //this rate is just for 2023 tax year. Can make this variable

        String taxRate = taxBracket.getTaxRate() + "%";
        if (taxBracket.getTaxRate() == 0) {
            taxRate = "No tax";
        }
        return TaxCalculationResponse.builder()
                .annualTax(taxAmount)
                .annualSuper(superAnnuation)
                .taxYearGiven(taxCalculationRequest.getTaxYear())
                .salaryAmountGiven(taxCalculationRequest.getSalaryAmount())
                .taxBase(taxBracket.getTaxBase())
                .taxRate(taxRate)
                .build();
    }
}
