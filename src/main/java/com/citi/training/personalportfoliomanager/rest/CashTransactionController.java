package com.citi.training.personalportfoliomanager.rest;

import com.citi.training.personalportfoliomanager.entities.CashTransaction;
import com.citi.training.personalportfoliomanager.entities.Portfolio;
import com.citi.training.personalportfoliomanager.service.CashTransactionService;
import org.hibernate.type.LocalDateTimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/portfolio/cash_accounts")
@CrossOrigin
public class CashTransactionController {
    @Autowired
    private CashTransactionService cashTransactionService;

    @RequestMapping(method = RequestMethod.GET, value="/all")
    public Collection<CashTransaction> getAll(){
        return cashTransactionService.getAllCashTransactions();
    }

    /**
     * Gets the account with id account_id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{account_id}")
    public CashTransaction getAccount(@PathVariable("account_id") int account_id){
        return cashTransactionService.get(account_id);
    }

    @RequestMapping(method = RequestMethod.GET, value="/value/{accountNumber}")
    public Double getCashAccountValue(@PathVariable("accountNumber") int accountNumber){
        return cashTransactionService.getCashAccountValue(accountNumber);
    }
    /**
     * Gets the Cash value of all accounts
     * @return (int) the cash value of all the accounts in this portfolio
     */
//    @RequestMapping(method = RequestMethod.GET, value="/cashvalue")
//    public double getCashValue(){
//        return cashTransactionService.getCashValue();
//    }


//    /**
//     * Adds a new CashTransaction to the CashTransaction table
//     * @param CashTransaction
//     */
//    @RequestMapping(method = RequestMethod.POST, value="/")
//    public void addAccount(@RequestBody CashTransaction ct) {
//        cashTransactionService.addNewCashTransaction(ct);
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/deposit/{id}/{value}")
    public void deposit(@PathVariable("id") int id, @PathVariable("value") double value){
        //need to add checks to ensure users aren't depositing negative values
        cashTransactionService.deposit(id, value);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/withdraw/{id}/{value}")
    public void withdraw(@PathVariable("id") int id, @PathVariable("value") double value){
        //need to add checks to ensure users aren't withdrawing negative values
        //need to ensure users are not withdrawing more then they have
        cashTransactionService.withdraw(id, value);
    }
}
