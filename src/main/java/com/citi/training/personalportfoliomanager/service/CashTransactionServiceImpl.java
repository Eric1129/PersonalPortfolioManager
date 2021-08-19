package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.CashTransaction;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CashTransactionServiceImpl implements CashTransactionService{
    @Override
    public Collection<CashTransaction> getAllCashAccounts() {
        return null;
    }

    @Override
    public CashTransaction get(int account_id) {
        return null;
    }

    @Override
    public double getNetWorth() {
        return 0;
    }

    @Override
    public double getCashValue() {
        return 0;
    }

    @Override
    public void addNewCashTransaction(CashTransaction account) {

    }

    @Override
    public void deposit(int id, double value) {

    }

    @Override
    public void withdraw(int id, double value) {

    }
}
