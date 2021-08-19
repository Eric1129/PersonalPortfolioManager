package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.CashTransaction;
import com.citi.training.personalportfoliomanager.repo.CashTransactionRepository;
import com.citi.training.personalportfoliomanager.repo.PortfolioRepository;
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

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Override
    public Collection<CashTransaction> getAllCashTransactions(){
        return cashTransactionRepository.findAll();
    }

    @Override
    public CashTransaction get(int transaction_id) {
        return cashTransactionRepository.findById(transaction_id).get();
    }

    @Override
    public boolean deposit(int id, double value) {
        if(!portfolioRepository.existsById(id)){
            return false;
        }
        CashTransaction newCT = new CashTransaction();
        newCT.setDate(LocalDateTime.now());
        newCT.setValue(value);
        newCT.setDepositOrCashOut("deposit");
        newCT.setAccountNumber(id);
        cashTransactionRepository.save(newCT);
        return true;
    }

    @Override
    public boolean withdraw(int id, double value) {
        // Todo: future check the balance of this account is greater or equals than withdraw value
        if(!portfolioRepository.existsById(id) ){
            return false;
        }
        CashTransaction newCT = new CashTransaction();
        newCT.setDate(LocalDateTime.now());
        newCT.setValue(value);
        newCT.setDepositOrCashOut("cash_out");
        newCT.setAccountNumber(id);
        cashTransactionRepository.save(newCT);
        return true;
    }
}
