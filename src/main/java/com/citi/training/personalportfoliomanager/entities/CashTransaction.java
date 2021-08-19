package com.citi.training.personalportfoliomanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="cash_transactions")
public class CashTransaction {

    @Id
    @Column(name = "transaction_number", nullable = false)
    private int transactionNumber;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "account_number", nullable = false)
    private int accountNumber;

    public String getDepositOrCashOut() {
        return depositOrCashOut;
    }

    public void setDepositOrCashOut(String depositOrCashOut) {
        this.depositOrCashOut = depositOrCashOut;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Column(name = "deposit_or_cash_out", nullable = false)
    private String depositOrCashOut;

    @Column(name = "value", nullable = false)
    private int value;

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


}
