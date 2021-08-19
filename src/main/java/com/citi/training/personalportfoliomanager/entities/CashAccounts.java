package com.citi.training.personalportfoliomanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CashAccounts {

    @Id
    @Column(name = "transaction_number", nullable = false)
    private Integer transactionNumber;

}
