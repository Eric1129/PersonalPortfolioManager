package com.citi.training.personalportfoliomanager.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="cash_transactions")
public class CashTransaction {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "transaction_number")
    private Integer transactionNumber;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "account_number", nullable = false)
    private Integer accountNumber;

    @Column(name = "deposit_or_cash_out", nullable = false)
    private String depositOrCashOut;

    @Column(name = "value", nullable = false)
    private Double value;

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

    public String getDepositOrCashOut() {
        return depositOrCashOut;
    }

    public void setDepositOrCashOut(String depositOrCashOut) {
        this.depositOrCashOut = depositOrCashOut;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
