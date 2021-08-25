package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.InvestmentTransaction;

import java.io.IOException;
import java.util.Collection;

public interface InvestmentTransactionService {
    Collection<InvestmentTransaction> getAllPortfolios();

    InvestmentTransaction get(Integer transaction_id);

    Double getInvestmentValue() throws IOException;

    Boolean buy(Integer accountNumber, Integer cashAccountNumber, String ticker, Integer amount) throws IOException;

    Boolean sell(Integer accountNumber, Integer cashAccountNumber, String ticker, Integer amount) throws IOException;

    Double getInvestmentValueForSingleAccount(Integer accountNumber) throws IOException;

    Double getStockPrice(String ticker) throws IOException;
}