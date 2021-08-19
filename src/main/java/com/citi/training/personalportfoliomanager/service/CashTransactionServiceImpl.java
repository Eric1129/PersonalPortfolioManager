package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.CashTransaction;
import com.citi.training.personalportfoliomanager.repo.CashTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class CashTransactionServiceImpl implements CashTransactionService{

    @Autowired
    private CashTransactionRepository cashTransactionRepository;

    @Override
    public Collection<CashTransaction> getAllCashTransactions(){
        return cashTransactionRepository.findAll();
    }

    @Override
    public CashTransaction get(int transaction_id) {
        return cashTransactionRepository.findById(transaction_id).get();
    }

    @Override
    public void deposit(int id, double value) {
        CashTransaction newCT = new CashTransaction();
        newCT.setDate(LocalDateTime.now());
        newCT.setValue(value);
        newCT.setDepositOrCashOut("deposit");
        newCT.setAccountNumber(id);
        cashTransactionRepository.save(newCT);
    }

    @Override
    public void withdraw(int id, double value) {
        CashTransaction newCT = new CashTransaction();
        newCT.setDate(LocalDateTime.now());
        newCT.setValue(value);
        newCT.setDepositOrCashOut("cash_out");
        newCT.setAccountNumber(id);
        cashTransactionRepository.save(newCT);
    }
}
