package com.citi.training.personalportfoliomanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="investment_accounts")
public class InvestmentAccounts implements Serializable{

    @Id
    @Column(name = "transaction_number", nullable = false)
    private int transactionNumber;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "account_number", nullable = false)
    private int accountNumber;

    @Column(name = "buy_or_sell", nullable = false)
    private String buyOrSell;

    @Column(name = "ticker", nullable = false)
    private String ticker;

    @Column(name = "face_value", nullable = false)
    private double faceValue;

    @Column(name = "amount", nullable = false)
    private int amount;

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(double faceValue) {
        this.faceValue = faceValue;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
