package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.InvestmentTransaction;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface InvestmentTransactionService {
    Collection<InvestmentTransaction> getAllPortfolios();

    InvestmentTransaction get(Integer transaction_id);

    Double getInvestmentValue() throws IOException;

    Boolean buy(Integer accountNumber, Integer cashAccountNumber, String ticker, Integer amount) throws IOException;

    Boolean sell(Integer accountNumber, Integer cashAccountNumber, String ticker, Integer amount) throws IOException;

    Double getInvestmentValueForSingleAccount(Integer accountNumber) throws IOException;

    List<ObjectNode> getInvestments(Integer accountNumber) throws IOException;

    Double getStockPrice(String ticker) throws IOException;
}