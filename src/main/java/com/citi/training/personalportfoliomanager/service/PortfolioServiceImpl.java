package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.Portfolio;
import com.citi.training.personalportfoliomanager.repo.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Override
    public Collection<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }
}
