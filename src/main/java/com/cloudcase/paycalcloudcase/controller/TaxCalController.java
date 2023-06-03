package com.cloudcase.paycalcloudcase.controller;

import com.cloudcase.paycalcloudcase.json.TaxCalculationRequest;
import com.cloudcase.paycalcloudcase.json.TaxCalculationResponse;
import com.cloudcase.paycalcloudcase.services.TaxCalculationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping(value = TaxCalController.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class TaxCalController {

    public static final String BASE_URL = "/taxcal";

    @Autowired
    private TaxCalculationService taxCalculationService;


    /**
     * This method is used to calculate tax and super amount
     *
     * @param taxCalculationResquest
     * @return
     * @throws RuntimeException
     */
    @PostMapping
    public TaxCalculationResponse getTaxAndSuperAmount(@Valid @RequestBody TaxCalculationRequest taxCalculationResquest) throws RuntimeException {
        String methodName = "getTaxAndSuperAmount";
        log.info("Entering " + methodName);
        try {
            return taxCalculationService.getTaxAndSuperAmount(taxCalculationResquest);
        } catch (RuntimeException e) {
            log.error("Exception in " + methodName + " " + e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

}
