package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.CashTransaction;
import com.citi.training.personalportfoliomanager.repo.CashTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CashTransactionServiceImpl implements CashTransactionService{

    @Autowired
    private CashTransactionRepository cashTransactionRepository;

    @Override
    public Collection<CashTransaction> getAllCashAccounts() {
        return cashTransactionRepository.findAll();
    }

    @Override
    public CashTransaction get(int account_id) {
        return cashTransactionRepository.findById(account_id).get();
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
