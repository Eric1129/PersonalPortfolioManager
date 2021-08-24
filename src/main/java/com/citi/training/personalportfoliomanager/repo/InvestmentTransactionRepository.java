package com.citi.training.personalportfoliomanager.repo;

import com.citi.training.personalportfoliomanager.entities.InvestmentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentTransactionRepository extends JpaRepository<InvestmentTransaction, Integer> {

    public List<InvestmentTransaction> findByTickerAndAccountNumber(String ticker, Integer accountNumber);

    public List<InvestmentTransaction> findInvestmentTransactionsByAccountNumber(Integer accountNumber);
}