package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.InvestmentTransaction;
import com.citi.training.personalportfoliomanager.repo.CashTransactionRepository;
import com.citi.training.personalportfoliomanager.repo.InvestmentTransactionRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public class InvestmentTransactionServiceImpl implements InvestmentTransactionService {

    @Autowired
    private InvestmentTransactionRepository investmentTransactionRepository;

    @Autowired
    private CashTransactionService cashTransactionService;

    @Override
    public Collection<InvestmentTransaction> getAllPortfolios() {
        return investmentTransactionRepository.findAll();
    }

    @Override
    public InvestmentTransaction get(Integer transaction_id) {
        return investmentTransactionRepository.findById(transaction_id).get();
    }

    @Override
    public double getInvestmentValue() {
        //will be implemented soon
        //need to get the amount of tickers held in each investment account
        //once we get those, we can go through each ticker, and calculate the total value by multiplying the amount with the current price
        //we get the current price of the stock using the yahoo finance API
        //add them all up and we have the total investment value
        return 0;
    }

    @Override
    public Boolean buy(Integer accountNumber, Integer cashAccountNumber, String ticker, Integer amount) throws IOException {
        //to do, check cashAccount to see if theres enough money for the transaction
        if(amount <= 0){
            return false;
        }
        InvestmentTransaction ret = new InvestmentTransaction();
        ret.setTicker(ticker);
        ret.setAccountNumber(accountNumber);
        ret.setFaceValue(getStockPrice(ticker));
        ret.setDate(LocalDateTime.now());
        ret.setAmount(amount);
        ret.setBuyOrSell("buy");
        cashTransactionService.withdraw(cashAccountNumber, getStockPrice(ticker) * amount);
        investmentTransactionRepository.save(ret);
        return true;
    }

    @Override
    public Boolean sell(Integer accountNumber, Integer cashAccountNumber, String ticker, Integer amount) throws IOException {
        //get investment transacations by the ticker and account number, check each transaction, add to total amount if its
        //a buy, subtract if its a sell, then check if there are enough stocks in teh account to sell the amount requested by user
        InvestmentTransaction ret = new InvestmentTransaction();
        List<InvestmentTransaction> accountTransactionsForTicker = investmentTransactionRepository.findByTickerAndAccount_number(ticker, accountNumber);

        int totalAmountofTickerHeldInAccount = 0;
        for(InvestmentTransaction transaction: accountTransactionsForTicker){
            if(transaction.getBuyOrSell().equals("buy")){
                totalAmountofTickerHeldInAccount+=transaction.getAmount();
            }else{
                totalAmountofTickerHeldInAccount-=transaction.getAmount();
            }
        }

        //check if theres enough stock in the account to sell the amount we want to sell, and that the amount is a valid number
        if(amount <= 0 || amount > totalAmountofTickerHeldInAccount){
            return false;
        }
        //set the values for the new transaction
        ret.setAccountNumber(accountNumber);
        ret.setAmount(amount);
        ret.setDate(LocalDateTime.now());
        ret.setTicker(ticker);
        ret.setFaceValue(getStockPrice(ticker));
        ret.setBuyOrSell("sell");
        cashTransactionService.deposit(cashAccountNumber, getStockPrice(ticker) * amount);
        investmentTransactionRepository.save(ret);
        return true;
    }

    public Double getStockPrice(String ticker) throws IOException {
        return YahooFinance.get(ticker).getQuote().getPrice().doubleValue();
    }
}