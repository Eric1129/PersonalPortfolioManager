package com.citi.training.personalportfoliomanager.service;

import com.citi.training.personalportfoliomanager.entities.InvestmentTransaction;
import com.citi.training.personalportfoliomanager.repo.InvestmentTransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class InvestmentTransactionServiceImpl implements InvestmentTransactionService {

    @Autowired
    private ObjectMapper mapper;

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

    /**
     *
     * @return the total value of all investments made by the portfolio manager
     * @throws IOException
     */
    @Override
    public Double getInvestmentValue() throws IOException {
        //will be implemented soon
        //need to get the amount of tickers held in each investment account
        //once we get those, we can go through each ticker, and calculate the total value by multiplying the amount with the current price
        //we get the current price of the stock using the yahoo finance API
        //add them all up and we have the total investment value

        Double ret = 0.0;
        List<InvestmentTransaction> transactionList = investmentTransactionRepository.findAll();
        Map<String, Integer> tickersAndAmounts = new HashMap<>();
        //Step 1, go through all transactions to get each ticker held
        for(InvestmentTransaction transaction: transactionList){
            if(transaction.getBuyOrSell().equals("buy")){
                tickersAndAmounts.put(transaction.getTicker(), tickersAndAmounts.getOrDefault(transaction.getTicker(),0) + transaction.getAmount());

            }else{
                tickersAndAmounts.put(transaction.getTicker(), tickersAndAmounts.getOrDefault(transaction.getTicker(),0) - transaction.getAmount());
                if(tickersAndAmounts.get(transaction.getTicker()) <= 0){
                    tickersAndAmounts.remove(transaction.getTicker());
                }
            }
        }

        //Step 2, go through the tickers, obtain the current price for each ticker, and use it to calculate the total value of the holding
        for(String ticker: tickersAndAmounts.keySet()){
            double price = this.getStockPrice(ticker);
            ret += price * tickersAndAmounts.get(ticker);
        }
        return ret;
    }


    /**
     *
     * @param accountNumber: the investment account that the stock will be purchased into
     * @param cashAccountNumber: the cash account that will be used to fund the purchase
     * @param ticker: the ticker of the stock that will be bought
     * @param amount: the amount of stock to be bought
     * @return boolean indicating whether or not the purchase was successful
     * @throws IOException
     */
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

    /**
     *
     * @param accountNumber: the investment account that the stock will be sold from
     * @param cashAccountNumber: the cash account that the cash from the sale will be deposited into
     * @param ticker: the ticker of the stock that will be sold
     * @param amount: the amount of stock to be sold
     * @return boolean indicating whether or not the sale was successful
     * @throws IOException
     */
    @Override
    public Boolean sell(Integer accountNumber, Integer cashAccountNumber, String ticker, Integer amount) throws IOException {
        //get investment transacations by the ticker and account number, check each transaction, add to total amount if its
        //a buy, subtract if its a sell, then check if there are enough stocks in teh account to sell the amount requested by user
        InvestmentTransaction ret = new InvestmentTransaction();
        List<InvestmentTransaction> accountTransactionsForTicker = investmentTransactionRepository.findByTickerAndAccountNumber(ticker, accountNumber);

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

    /**
     *
     * @param accountNumber: Account whose value is to be determined
     * @return the value of investments held in the account
     * @throws IOException
     */
    @Override
    public Double getInvestmentValueForSingleAccount(Integer accountNumber) throws IOException {

        //Exact same implementation as getInvestmentValue except for find transaction id's for particular account
        Double ret = 0.0;
        List<InvestmentTransaction> transactionList = investmentTransactionRepository.findInvestmentTransactionsByAccountNumber(accountNumber);
        Map<String, Integer> tickersAndAmounts = new HashMap<>();
        //Step 1, go through all transactions to get each ticker held
        for(InvestmentTransaction transaction: transactionList){
            if(transaction.getBuyOrSell().equals("buy")){
                tickersAndAmounts.put(transaction.getTicker(), tickersAndAmounts.getOrDefault(transaction.getTicker(),0) + transaction.getAmount());

            }else{
                tickersAndAmounts.put(transaction.getTicker(), tickersAndAmounts.getOrDefault(transaction.getTicker(),0) - transaction.getAmount());
                if(tickersAndAmounts.get(transaction.getTicker()) <= 0){
                    tickersAndAmounts.remove(transaction.getTicker());
                }
            }
        }

        //Step 2, go through the tickers, obtain the current price for each ticker, and use it to calculate the total value of the holding
        for(String ticker: tickersAndAmounts.keySet()){
            double price = this.getStockPrice(ticker);
            ret += price * tickersAndAmounts.get(ticker);
        }
        return ret;
    }

    public List<ObjectNode> getInvestments(Integer accountNumber) throws IOException {
        //need to go through all transactions for this account and g
        List<ObjectNode> ret = new ArrayList<>();

        List<InvestmentTransaction> transactionList = investmentTransactionRepository.findInvestmentTransactionsByAccountNumber(accountNumber);
        Map<String, Integer> tickersAndAmounts = new HashMap<>();
        //Step 1, go through all transactions to get each ticker held
        for(InvestmentTransaction transaction: transactionList){
            if(transaction.getBuyOrSell().equals("buy")){
                tickersAndAmounts.put(transaction.getTicker(), tickersAndAmounts.getOrDefault(transaction.getTicker(),0) + transaction.getAmount());

            }else{
                tickersAndAmounts.put(transaction.getTicker(), tickersAndAmounts.getOrDefault(transaction.getTicker(),0) - transaction.getAmount());
                if(tickersAndAmounts.get(transaction.getTicker()) <= 0){
                    tickersAndAmounts.remove(transaction.getTicker());
                }
            }
        }

        for(String ticker: tickersAndAmounts.keySet()){
            ObjectNode toAdd = mapper.createObjectNode();
            toAdd.put("ticker", ticker);
            toAdd.put("amount", tickersAndAmounts.get(ticker));
            toAdd.put("current_price", this.getStockPrice(ticker));
            ret.add(toAdd);
        }
        return ret;
    }

    public Double getStockPrice(String ticker) throws IOException {
        return YahooFinance.get(ticker).getQuote().getPrice().doubleValue();
    }

//    @Override
//    public HashMap<String,Double> getGainers(int accountNumber) throws IOException {
//        List<InvestmentTransaction> transactionList = investmentTransactionRepository.findInvestmentTransactionsByAccountNumber(accountNumber);
//        HashMap<String,Integer> hashMap = new HashMap<>();
//        for(InvestmentTransaction investmentTransaction : transactionList){
//            String ticker = investmentTransaction.getTicker();
//            Integer amount = investmentTransaction.getAmount();
//            if(investmentTransaction.getBuyOrSell().equals("buy")){
//                if(!hashMap.containsKey(ticker)){
//                    hashMap.put(ticker, amount);
//                } else {
//                    Integer temp = hashMap.get(investmentTransaction.getTicker());
//                    hashMap.put(investmentTransaction.getTicker(), temp + investmentTransaction.getAmount());
//                }
//            } else {
//                if(!hashMap.containsKey(ticker)){
//                    hashMap.put(ticker, -amount);
//                } else {
//                    Integer temp = hashMap.get(investmentTransaction.getTicker());
//                    hashMap.put(investmentTransaction.getTicker(), temp - investmentTransaction.getAmount());
//                }
//            }
//        }
//        hashMap.
//
//
//        return new HashMap<>();
//    }

//    @Override
//    public Double[] getLosers() throws IOException {
//        return new Double[0];
//    }

}