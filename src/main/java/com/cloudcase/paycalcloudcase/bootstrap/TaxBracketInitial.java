package com.cloudcase.paycalcloudcase.bootstrap;

import com.cloudcase.paycalcloudcase.jpa.TaxBracket;
import com.cloudcase.paycalcloudcase.jpa.TaxBracketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TaxBracketInitial implements CommandLineRunner {

    private final TaxBracketRepository taxBracketRepository;

    public TaxBracketInitial(TaxBracketRepository taxBracketRepository) {
        this.taxBracketRepository = taxBracketRepository;
    }

    /**
     * This method is called when the application starts up; it is used to populate the database with some initial data.
     * @param args incoming main method arguments
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        TaxBracket taxBracket1 = new TaxBracket();
        TaxBracket taxBracket2 = new TaxBracket();
        TaxBracket taxBracket3 = new TaxBracket();
        TaxBracket taxBracket4 = new TaxBracket();
        TaxBracket taxBracket5 = new TaxBracket();

        taxBracket1.setTaxRate(0);
        taxBracket1.setTaxBase(0);
        taxBracket1.setTaxableIncomeStart(0);
        taxBracket1.setTaxableIncomeEnd(18200);
        taxBracket1.setTaxYear(2023);

        taxBracket2.setTaxRate(19);
        taxBracket2.setTaxBase(0);
        taxBracket2.setTaxableIncomeStart(18201);
        taxBracket2.setTaxableIncomeEnd(37000);
        taxBracket2.setTaxYear(2023);

        taxBracket3.setTaxRate(32);
        taxBracket3.setTaxBase(3572);
        taxBracket3.setTaxableIncomeStart(37001);
        taxBracket3.setTaxableIncomeEnd(90000);
        taxBracket3.setTaxYear(2023);

        taxBracket4.setTaxRate(37);
        taxBracket4.setTaxBase(20797);
        taxBracket4.setTaxableIncomeStart(90001);
        taxBracket4.setTaxableIncomeEnd(180000);
        taxBracket4.setTaxYear(2023);

        taxBracket5.setTaxRate(45);
        taxBracket5.setTaxBase(54097);
        taxBracket5.setTaxableIncomeStart(180001);
        taxBracket5.setTaxableIncomeEnd(999999999);
        taxBracket5.setTaxYear(2023);

        taxBracketRepository.save(taxBracket1);
        taxBracketRepository.save(taxBracket2);
        taxBracketRepository.save(taxBracket3);
        taxBracketRepository.save(taxBracket4);
        taxBracketRepository.save(taxBracket5);
    }
}
