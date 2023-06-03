package com.cloudcase.paycalcloudcase.jpa;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class TaxBracketDataStore {

    private final TaxBracketRepository taxBracketRepository;

    public List<TaxBracket> findAll() {
        return taxBracketRepository.findAll();
    }
}
