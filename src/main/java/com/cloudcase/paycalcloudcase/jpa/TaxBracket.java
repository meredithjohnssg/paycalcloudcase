package com.cloudcase.paycalcloudcase.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "TAXBRACKETS")
public class TaxBracket {

    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @NotNull
    private int taxableIncomeStart;

    @NotNull
    private int taxableIncomeEnd;

    private int taxBase;
    private int taxRate;
    private int taxYear;
}
