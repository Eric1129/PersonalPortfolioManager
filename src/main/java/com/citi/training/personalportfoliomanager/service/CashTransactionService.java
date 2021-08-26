package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.CashTransaction;

import java.time.LocalDateTime;
import java.util.Collection;

public interface CashTransactionService {
    Collection<CashTransaction> getAllCashTransactions();

    CashTransaction get(int account_id);

    Double getCashValue();

    Double getCashAccountValue(int accountNumber);

    boolean deposit(int id, double value);

    boolean withdraw(int id, double value);

}
