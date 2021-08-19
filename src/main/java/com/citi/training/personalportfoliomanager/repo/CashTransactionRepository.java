package com.citi.training.personalportfoliomanager.repo;

import com.citi.training.personalportfoliomanager.entities.CashTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashTransactionRepository extends JpaRepository<CashTransaction, Integer> {
    List<CashTransaction> findByAccountType(String type);
}
