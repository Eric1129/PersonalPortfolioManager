package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.CashTransaction;
import com.citi.training.personalportfoliomanager.repo.CashTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Collection<CashTransaction> cashTransactionList = new ArrayList<>();
        cashTransactionList = cashTransactionRepository.findAll();
        double ret = 0;

        for(CashTransaction account: cashTransactionList){
            if(account.getDepositOrCashOut().equals("deposit")){
                ret += account.getValue();
            }else{
                ret -= account.getValue();
            }
        }

        return ret;
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
