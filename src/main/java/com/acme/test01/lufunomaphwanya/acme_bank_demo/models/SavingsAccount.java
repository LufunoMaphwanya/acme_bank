package com.acme.test01.lufunomaphwanya.acme_bank_demo.models;

import com.acme.test01.lufunomaphwanya.acme_bank_demo.exceptions.AccountNotFoundException;
import com.acme.test01.lufunomaphwanya.acme_bank_demo.exceptions.WithdrawalAmountTooLargeException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsAccount extends Account {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    private String type = "SAVINGS";

    private int balance;

    private Long accountNumber;



}