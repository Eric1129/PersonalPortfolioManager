package com.citi.training.personalportfoliomanager.rest;


import com.citi.training.personalportfoliomanager.entities.InvestmentTransaction;
import com.citi.training.personalportfoliomanager.service.InvestmentTransactionService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/investment")
@CrossOrigin

public class InvestmentTransactionController{

    @Autowired
    private InvestmentTransactionService investmentTransactionService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Collection<InvestmentTransaction> getAll(){
        return investmentTransactionService.getAllPortfolios();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{transaction_id}")
    public InvestmentTransaction getTransaction(@PathVariable("transaction_id") int transaction_id){
        return investmentTransactionService.get(transaction_id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/value")
    public Double getInvestmentValue() throws IOException {
        return investmentTransactionService.getInvestmentValue();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/buy")
    public void buy(@RequestBody BuyForm buyForm) throws IOException {
        investmentTransactionService.buy(buyForm.getAccountNumber(), buyForm.getCashAccountNumber(), buyForm.getTicker(), buyForm.getAmount());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sell")
    public void sell(@RequestBody BuyForm buyForm) throws IOException {
        investmentTransactionService.sell(buyForm.getAccountNumber(), buyForm.getCashAccountNumber(), buyForm.getTicker(), buyForm.getAmount());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/value/{accountNumber}")
    public Double getValueOfAccount(@PathVariable("accountNumber") int accountNumber) throws IOException {
        return investmentTransactionService.getInvestmentValueForSingleAccount(accountNumber);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stock/{tickerId}")
    public Double getStockPrice(@PathVariable("tickerId") String tickerId) throws IOException {
        return investmentTransactionService.getStockPrice(tickerId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/investments/{accountNumber}")
    //need a list of hashmaps
    //{ticker: "TSLA", amount: 0, current_price: 0}
    public List<ObjectNode> getInvestments(@PathVariable("accountNumber") int accountNumber) throws IOException {
        return investmentTransactionService.getInvestments(accountNumber);
    }

//    @RequestMapping(method = RequestMethod.GET, value="/gainers/{gainerIndex}")
//    public double[] getGainers(@PathVariable("gainerIndex") String gainerIndex) throws IOException {
//        for()
//        return investmentTransactionService;
//    }

}

class BuyForm{
    private Integer accountNumber;
    private Integer cashAccountNumber;
    private String ticker;

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getCashAccountNumber() {
        return cashAccountNumber;
    }

    public void setCashAccountNumber(Integer cashAccountNumber) {
        this.cashAccountNumber = cashAccountNumber;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    private Integer amount;

    BuyForm(Integer accountNumber, Integer cashAccountNumber, String ticker, Integer amount) {
        this.accountNumber = accountNumber;
        this.cashAccountNumber = cashAccountNumber;
        this.ticker = ticker;
        this.amount = amount;
    }
}