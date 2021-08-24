package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.Portfolio;


import javax.sound.sampled.Port;
import java.util.Collection;

public interface PortfolioService {
    Collection<Portfolio> getAllPortfolios();

    Portfolio get(int account_id);

    double getNetWorth();

    double getCashValue();

    double getInvestmentValue();

    void addNewAccount(Portfolio account);

    void addNewAccount(String account_type);

    void deleteAccount(int id);

    void deleteAccount(Portfolio account);

    void deposit(int id, double value);

    void withdraw(int id, double value);
}
