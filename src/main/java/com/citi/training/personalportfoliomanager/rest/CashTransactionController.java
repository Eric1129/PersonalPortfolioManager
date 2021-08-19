package com.citi.training.personalportfoliomanager.rest;

import com.citi.training.personalportfoliomanager.entities.CashTransaction;
import com.citi.training.personalportfoliomanager.entities.Portfolio;
import com.citi.training.personalportfoliomanager.service.CashTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/portfolio/cash_accounts")
public class CashTransactionController {
    @Autowired
    private CashTransactionService cashAccountsService;

    @RequestMapping(method = RequestMethod.GET, value="/all")
    public Collection<CashTransaction> getAll(){
        return cashAccountsService.getAllCashAccounts();
    }

    /**
     * Gets the account with id account_id
     */
    @RequestMapping(method = RequestMethod.GET, value = "{account_id}")
    public CashTransaction getAccount(@PathVariable("account_id") int account_id){
        return cashAccountsService.get(account_id);
    }

    /**
     * Gets the Net worth of all accounts
     * @return (int) the net worth of all the accounts in this portfolio
     */
    @RequestMapping(method = RequestMethod.GET, value="/networth")
    public double getNetWorth(){
        return cashAccountsService.getNetWorth();
    }

    /**
     * Gets the Cash value of all accounts
     * @return (int) the cash value of all the accounts in this portfolio
     */
    @RequestMapping(method = RequestMethod.GET, value="/cashvalue")
    public double getCashValue(){
        return cashAccountsService.getCashValue();
    }


    /**
     * Adds a new CashTransaction to the CashTransaction table
     * @param CashTransaction
     */
    @RequestMapping(method = RequestMethod.POST, value="/")
    public void addAccount(@RequestBody CashTransaction ct) {
        cashAccountsService.addNewCashTransaction(ct);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/deposit/{id}/{value}")
    public void deposit(@PathVariable("id") int id, @PathVariable("value") double value){
        //need to add checks to ensure users aren't depositing negative values
        cashAccountsService.deposit(id, value);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/withdraw/{id}/{value}")
    public void withdraw(@PathVariable("id") int id, @PathVariable("value") double value){
        //need to add checks to ensure users aren't withdrawing negative values
        //need to ensure users are not withdrawing more then they have
        cashAccountsService.withdraw(id, value);
    }
}
