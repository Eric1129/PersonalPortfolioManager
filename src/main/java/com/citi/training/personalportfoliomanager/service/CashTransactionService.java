package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.CashTransaction;

import java.util.Collection;

public interface CashTransactionService {
    Collection<CashTransaction> getAllCashAccounts();

    CashTransaction get(int account_id);

    double getNetWorth();

    double getCashValue();

    void addNewCashTransaction(CashTransaction account);

    void deposit(int id, double value);

    void withdraw(int id, double value);
}
