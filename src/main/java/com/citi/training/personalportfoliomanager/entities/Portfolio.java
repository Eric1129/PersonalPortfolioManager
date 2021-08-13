package com.citi.training.personalportfoliomanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="portfolio")
public class Portfolio implements Serializable {

    @Id
    @Column(name = "account_number", nullable = false)
    private Integer accountNumber;

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }


}
