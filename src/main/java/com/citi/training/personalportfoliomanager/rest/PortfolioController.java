package com.citi.training.personalportfoliomanager.rest;


import com.citi.training.personalportfoliomanager.entities.Portfolio;
import com.citi.training.personalportfoliomanager.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @RequestMapping(method = RequestMethod.GET, value="/all")
    public Collection<Portfolio> getAll(){
        return portfolioService.getAllPortfolios();
    }

    /**
     * Gets the account with id account_id
     */
    @RequestMapping(method = RequestMethod.GET, value="/{account_id}")
    public Portfolio getAccount(@PathVariable("account_id") int account_id){
        return portfolioService.get(account_id);
    }

    /**
     * Gets the Net worth of all accounts
     * @return (int) the net worth of all the accounts in this portfolio
     */
    @RequestMapping(method = RequestMethod.GET, value="/networth")
    public double getNetWorth(){
        return portfolioService.getNetWorth();
    }

    /**
     * Gets the Cash value of all accounts
     * @return (int) the cash value of all the accounts in this portfolio
     */
    @RequestMapping(method = RequestMethod.GET, value="/cashvalue")
    public double getCashValue(){
        return portfolioService.getCashValue();
    }

    /**
     * Gets the Investment value of all accounts
     * @return (int) the investment value of all the accounts in this portfolio
     */
    @RequestMapping(method = RequestMethod.GET, value="/investmentvalue")
    public double getInvestmentValue(){
        return portfolioService.getInvestmentValue();
    }

    /**
     * Adds a new account to the portfolio table
     * @param account
     */
    @RequestMapping(method = RequestMethod.POST)
    public void addAccount(@RequestBody Portfolio account) {
        portfolioService.addNewAccount(account);
    }

    /**
     * Delets an existing account from the portfolio table
     * @param id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteAccount(@PathVariable("id") int id) {
        portfolioService.deleteAccount(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAccount(@RequestBody Portfolio account) {
        portfolioService.deleteAccount(account);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/deposit/{id}/{value}")
    public void deposit(@PathVariable("id") int id, @PathVariable("value") double value){
        //need to add checks to ensure users aren't depositing negative values
        portfolioService.deposit(id, value);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/add/{id}/{value}")
    public void withdraw(@PathVariable("id") int id, @PathVariable("value") double value){
        //need to add checks to ensure users aren't withdrawing negative values
        //need to ensure users are not withdrawing more then they have
        portfolioService.withdraw(id, value);
    }

}
