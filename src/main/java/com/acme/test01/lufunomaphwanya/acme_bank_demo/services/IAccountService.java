package com.acme.test01.lufunomaphwanya.acme_bank_demo.models;

import com.acme.test01.lufunomaphwanya.acme_bank_demo.exceptions.AccountNotFoundException;
import com.acme.test01.lufunomaphwanya.acme_bank_demo.exceptions.WithdrawalAmountTooLargeException;

public interface IAccountService {
    void openSavingsAccount(Long accountId, Long amountToDeposit);
    void openCurrentAccount(Long accountId);
    void withdraw(Long accountId, int amountToWithdraw)
            throws AccountNotFoundException, WithdrawalAmountTooLargeException;
    void deposit(Long accountId, int amountToDeposit)throws
            AccountNotFoundException;

}
