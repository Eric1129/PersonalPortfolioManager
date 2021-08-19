package com.citi.training.personalportfoliomanager.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="cash_transactions")
public class CashTransaction {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "transaction_number", nullable = false)
    private Integer transactionNumber;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "account_number", nullable = false)
    private Integer accountNumber;

    public String getDepositOrCashOut() {
        return depositOrCashOut;
    }

    public void setDepositOrCashOut(String depositOrCashOut) {
        this.depositOrCashOut = depositOrCashOut;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Column(name = "deposit_or_cash_out", nullable = false)
    private String depositOrCashOut;

    @Column(name = "value", nullable = false)
    private Double value;

    public int getTransactionNumber() {
        return transactionNumber;
    }

//    public void setTransactionNumber(int transactionNumber) {
//        this.transactionNumber = transactionNumber;
//    }

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
