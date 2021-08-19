package com.citi.training.personalportfoliomanager.service;

//import com.citi.training.personalportfoliomanager.entities.CashAccounts;
import com.citi.training.personalportfoliomanager.entities.Portfolio;
import com.citi.training.personalportfoliomanager.repo.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    //portfolio = account
    @Autowired
    private PortfolioRepository portfolioRepository;

//    @Autowired
//    private CashAccountRepository cashAccountRepository;

    @Override
    public Collection<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }

    @Override
    public Portfolio get(int account_id) {
        return portfolioRepository.findById(account_id).get();
    }

    @Override
    public double getNetWorth() {
//        Collection<Portfolio> portfolioList = new ArrayList<>();
//        portfolioList = portfolioRepository.findAll();
//        double ret = 0;
//        for(Portfolio account: portfolioList){
//            ret += account.getValue();
//        }
//        return ret;
        return 0;
    }

    @Override
    public double getCashValue() {
//        Collection<Portfolio> portfolioList = new ArrayList<>();
//        portfolioList = portfolioRepository.findAll();
//        double ret = 0;
//        for(Portfolio account: portfolioList){
//            if(account.getAccountType().equals("cash")){
//                ret += account.getValue();
//            }
//
//        }
//        return ret;
        return 0;

//        Collection<CashAccounts> cashAccountsList = new ArrayList<>();
//        cashAccountsList = cashAccountRepository.findAll();
//        double ret = 0;
//
//        for(CashAccount account: cashAccountsList)

    }

    @Override
    public double getInvestmentValue() {
//        Collection<Portfolio> portfolioList = new ArrayList<>();
//        portfolioList = portfolioRepository.findAll();
//        double ret = 0;
//        for(Portfolio account: portfolioList){
//            if(account.getAccountType().equals("investment")){
//                ret += account.getValue();
//            }
//
//        }
//        return ret;
        return 0;
    }

    @Override
    public void addNewAccount(Portfolio account) {
        portfolioRepository.save(account);
    }

    @Override
    public void deleteAccount(int id) {
        Portfolio toBeDeleted = portfolioRepository.findById(id).get();
        deleteAccount(toBeDeleted);
    }

    @Override
    public void deleteAccount(Portfolio account) {
        portfolioRepository.delete(account);
    }

    @Override
    public void deposit(int id, double value) {
//        if(value <= 0){
//            return;
//        }
//        Portfolio toBeDepositedInto = portfolioRepository.findById(id).get();
//        toBeDepositedInto.setValue(toBeDepositedInto.getValue() + value);
    }

    @Override
    public void withdraw(int id, double value) {
//        Portfolio toBeDepositedInto = portfolioRepository.findById(id).get();
//        if(value <= 0 || value > toBeDepositedInto.getValue()){
//            return;
//        }
//        toBeDepositedInto.setValue(toBeDepositedInto.getValue() - value);
    }


}
