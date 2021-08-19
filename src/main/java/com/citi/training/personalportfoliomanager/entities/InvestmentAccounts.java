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
    private Integer transactionNumber;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "account_number", nullable = false)
    private Integer accountNumber;

    @Column(name = "buy_or_sell", nullable = false)
    private String buyOrSell;

    @Column(name = "ticker", nullable = false)
    private String ticker;

    @Column(name = "face_value", nullable = false)
    private Double faceValue;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    public Integer getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Integer transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
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

    public Double getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Double faceValue) {
        this.faceValue = faceValue;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
