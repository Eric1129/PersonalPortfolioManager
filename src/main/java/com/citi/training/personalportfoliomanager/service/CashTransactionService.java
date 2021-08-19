package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.CashTransaction;

import java.time.LocalDateTime;
import java.util.Collection;

public interface CashTransactionService {
    Collection<CashTransaction> getAllCashTransactions();

    CashTransaction get(int account_id);

    void deposit(int id, double value);

    void withdraw(int id, double value);

}
