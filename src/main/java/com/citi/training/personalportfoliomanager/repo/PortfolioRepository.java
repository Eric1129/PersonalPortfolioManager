package com.citi.training.personalportfoliomanager.repo;

import com.citi.training.personalportfoliomanager.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

    //List<Portfolio> findByAccountType(String type);

    List<Portfolio> findPortfolioByAccountType(String accountType);
}
