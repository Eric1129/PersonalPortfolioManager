package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.InvestmentTransaction;

import java.io.IOException;
import java.util.Collection;

public interface InvestmentTransactionService {
    Collection<InvestmentTransaction> getAllPortfolios();

    InvestmentTransaction get(Integer account_id);

    double getInvestmentValue();

    Boolean buy(Integer accountNumber, Integer cashAccountNumber, String ticker, Integer amount) throws IOException;

    Boolean sell(Integer accountNumber, Integer cashAccountNumber, String ticker, Integer amount) throws IOException;
}