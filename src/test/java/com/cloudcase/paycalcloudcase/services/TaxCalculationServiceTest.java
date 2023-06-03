package com.cloudcase.paycalcloudcase.services;

import com.cloudcase.paycalcloudcase.jpa.TaxBracket;
import com.cloudcase.paycalcloudcase.jpa.TaxBracketDataStore;
import com.cloudcase.paycalcloudcase.json.TaxCalculationRequest;
import com.cloudcase.paycalcloudcase.json.TaxCalculationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

class TaxCalculationServiceTest {
    @InjectMocks
    private TaxCalculationService taxCalculationService;

    @Mock
    private TaxBracketDataStore taxBracketDataStore;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("Test getTaxAndSuperAmount - Success")
    @Test
    void getTaxAndSuperAmount_success() {

        //These object initialisations can be moved to a separate class for better readability
        TaxCalculationRequest taxCalculationRequest = new TaxCalculationRequest();
        taxCalculationRequest.setTaxYear(2023);
        taxCalculationRequest.setSalaryAmount(130000);

        TaxCalculationResponse taxCalculationResponse = new TaxCalculationResponse();
        taxCalculationResponse.setTaxYearGiven(2023);
        taxCalculationResponse.setSalaryAmountGiven(130000);
        taxCalculationResponse.setTaxRate("37%");
        taxCalculationResponse.setTaxBase(20797);
        taxCalculationResponse.setAnnualTax(68897);
        taxCalculationResponse.setAnnualSuper(13650);

        List<TaxBracket> taxBrackets = new ArrayList<>();
        TaxBracket taxBracket2023 = new TaxBracket();
        taxBracket2023.setTaxYear(2023);
        taxBracket2023.setTaxRate(37);
        taxBracket2023.setTaxBase(20797);
        taxBracket2023.setTaxableIncomeStart(90001);
        taxBracket2023.setTaxableIncomeEnd(180000);
        taxBrackets.add(taxBracket2023);

        doReturn(taxBrackets).when(taxBracketDataStore).findAll();
        TaxCalculationResponse theResponse = taxCalculationService.getTaxAndSuperAmount(taxCalculationRequest);
        assertThat(theResponse).isNotNull();
        assertThat(theResponse.getTaxYearGiven()).isEqualTo(2023);
        assertThat(theResponse.getSalaryAmountGiven()).isEqualTo(130000);
        assertThat(theResponse.getTaxRate()).isEqualTo("37%");
        assertThat(theResponse.getTaxBase()).isEqualTo(20797);
        assertThat(theResponse.getAnnualTax()).isEqualTo(68897);
        assertThat(theResponse.getAnnualSuper()).isEqualTo(13650);
    }


    @DisplayName("Test getTaxAndSuperAmount - Success - No Tax")
    @Test
    void getTaxAndSuperAmount_lowSalary_lowTax() {

        TaxCalculationRequest taxCalculationRequest = new TaxCalculationRequest();
        taxCalculationRequest.setTaxYear(2023);
        taxCalculationRequest.setSalaryAmount(10000);

        TaxCalculationResponse taxCalculationResponse = new TaxCalculationResponse();
        taxCalculationResponse.setTaxYearGiven(2023);
        taxCalculationResponse.setSalaryAmountGiven(10000);
        taxCalculationResponse.setTaxRate("No tax");
        taxCalculationResponse.setTaxBase(0);
        taxCalculationResponse.setAnnualTax(0);
        taxCalculationResponse.setAnnualSuper(1050);

        List<TaxBracket> taxBrackets = new ArrayList<>();
        TaxBracket taxBracket2023 = new TaxBracket();
        taxBracket2023.setTaxYear(2023);
        taxBracket2023.setTaxRate(0);
        taxBracket2023.setTaxBase(0);
        taxBracket2023.setTaxableIncomeStart(0);
        taxBracket2023.setTaxableIncomeEnd(18200);
        taxBrackets.add(taxBracket2023);

        doReturn(taxBrackets).when(taxBracketDataStore).findAll();
        TaxCalculationResponse theResponse = taxCalculationService.getTaxAndSuperAmount(taxCalculationRequest);
        assertThat(theResponse).isNotNull();
        assertThat(theResponse.getTaxYearGiven()).isEqualTo(2023);
        assertThat(theResponse.getSalaryAmountGiven()).isEqualTo(10000);
        assertThat(theResponse.getTaxRate()).isEqualTo("No tax");
        assertThat(theResponse.getTaxBase()).isEqualTo(0);
        assertThat(theResponse.getAnnualTax()).isEqualTo(0);
        assertThat(theResponse.getAnnualSuper()).isEqualTo(1050);
    }

    @DisplayName("Test getTaxAndSuperAmount - Fail - No Relevant Saved Tax Bracket")
    @Test
    void getTaxAndSuperAmount_noBracketSaved_Fail() {

        TaxCalculationRequest taxCalculationRequest = new TaxCalculationRequest();
        taxCalculationRequest.setTaxYear(1900);
        taxCalculationRequest.setSalaryAmount(10000);

        List<TaxBracket> taxBrackets = new ArrayList<>();

        doReturn(taxBrackets).when(taxBracketDataStore).findAll();
        assertThatThrownBy(() -> taxCalculationService.getTaxAndSuperAmount(taxCalculationRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("No tax bracket found for the given salary amount and tax year");
    }
}
