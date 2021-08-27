package com.citi.training.personalportfoliomanager.rest;


import com.citi.training.personalportfoliomanager.entities.Portfolio;
import com.citi.training.personalportfoliomanager.service.CashTransactionService;
import com.citi.training.personalportfoliomanager.service.InvestmentTransactionService;
import com.citi.training.personalportfoliomanager.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/portfolio")
@CrossOrigin
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private CashTransactionService cashTransactionService;

    @Autowired
    private InvestmentTransactionService investmentTransactionService;

    @RequestMapping(method = RequestMethod.GET, value="/all")
    public Collection<Portfolio> getAll(){
        return portfolioService.getAllPortfolios();
    }

    @RequestMapping(method = RequestMethod.GET, value="/investment_accounts")
    public Collection<Portfolio> getAllInvestmentAccounts(){
        return portfolioService.getAllInvestmentAccounts();
    }

    @RequestMapping(method = RequestMethod.GET, value="/cash_accounts")
    public Collection<Portfolio> getAllCashAccounts(){
        return portfolioService.getAllCashAccounts();
    }

    /**
     * Gets the account with id account_id
     */
    @RequestMapping(method = RequestMethod.GET, value = "{account_id}")
    public Portfolio getAccount(@PathVariable("account_id") int account_id){
        return portfolioService.get(account_id);
    }

    /**
     * Gets the Net worth of all accounts
     * @return (int) the net worth of all the accounts in this portfolio
     */
    @RequestMapping(method = RequestMethod.GET, value="/networth")
    public Double getNetWorth() throws IOException {
         return cashTransactionService.getCashValue() + investmentTransactionService.getInvestmentValue();
    }

    /**
     * Gets the Cash value of all accounts
     * @return (int) the cash value of all the accounts in this portfolio
     */
    @RequestMapping(method = RequestMethod.GET, value="/cashvalue")
    public double getCashValue(){
        return cashTransactionService.getCashValue();
    }

    /**
     * Gets the Investment value of all accounts
     * @return (int) the investment value of all the accounts in this portfolio
     */
    @RequestMapping(method = RequestMethod.GET, value="/investmentvalue")
    public double getInvestmentValue() throws IOException {
        return investmentTransactionService.getInvestmentValue();
    }

    /**
     Gets markets indices
     */
    @RequestMapping(method = RequestMethod.GET, value="/marketindices/SAP500")
    public double getSAP500() throws IOException {
        return YahooFinance.get("^GSPC").getQuote().getPrice().doubleValue();
    }
    @RequestMapping(method = RequestMethod.GET, value="/marketindices/SAP500/change")
    public double getSAP500Change() throws IOException {
        return YahooFinance.get("^GSPC").getQuote().getChangeInPercent().doubleValue();
    }
    @RequestMapping(method = RequestMethod.GET, value="/marketindices/DWJ")
    public double getDWJ() throws IOException {
        return YahooFinance.get("^DJI").getQuote().getPrice().doubleValue();
    }
    @RequestMapping(method = RequestMethod.GET, value="/marketindices/DWJ/change")
    public double getDWJChange() throws IOException {
        return YahooFinance.get("^DJI").getQuote().getChangeInPercent().doubleValue();
    }
    @RequestMapping(method = RequestMethod.GET, value="/marketindices/NSDQ")
    public double getNSDQ() throws IOException {
        return YahooFinance.get("^IXIC").getQuote().getPrice().doubleValue();
    }
    @RequestMapping(method = RequestMethod.GET, value="/marketindices/NSDQ/change")
    public double getNSDQChange() throws IOException {
        return YahooFinance.get("^IXIC").getQuote().getChangeInPercent().doubleValue();
    }

    /**
     * Adds a new account to the portfolio table
     * @param account
     */
    @RequestMapping(method = RequestMethod.POST, value="/addAccount")
    public void addAccount(@RequestBody Portfolio account) {
        portfolioService.addNewAccount(account);
    }

    @RequestMapping(method = RequestMethod.POST, value="/add_account/{account_type}")
    public void addAccount(@PathVariable("account_type") String account_type){
        portfolioService.addNewAccount(account_type);
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



    //to be removed
    @RequestMapping(method = RequestMethod.PUT, value = "/deposit/{id}/{value}")
    public void deposit(@PathVariable("id") int id, @PathVariable("value") double value){
        //need to add checks to ensure users aren't depositing negative values
        portfolioService.deposit(id, value);
    }


    //to be removed
    @RequestMapping(method = RequestMethod.PUT, value = "/withdraw/{id}/{value}")
    public void withdraw(@PathVariable("id") int id, @PathVariable("value") double value){
        //need to add checks to ensure users aren't withdrawing negative values
        //need to ensure users are not withdrawing more then they have
        portfolioService.withdraw(id, value);
    }

}
