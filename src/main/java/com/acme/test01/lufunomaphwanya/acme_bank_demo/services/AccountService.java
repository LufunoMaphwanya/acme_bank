package com.acme.test01.lufunomaphwanya.acme_bank_demo.services;

import com.acme.test01.lufunomaphwanya.acme_bank_demo.exceptions.AccountNotFoundException;
import com.acme.test01.lufunomaphwanya.acme_bank_demo.exceptions.WithdrawalAmountTooLargeException;
import com.acme.test01.lufunomaphwanya.acme_bank_demo.models.Account;
import com.acme.test01.lufunomaphwanya.acme_bank_demo.models.CurrentAccount;
import com.acme.test01.lufunomaphwanya.acme_bank_demo.models.IAccountService;
import com.acme.test01.lufunomaphwanya.acme_bank_demo.models.SavingsAccount;
import com.acme.test01.lufunomaphwanya.acme_bank_demo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService
{

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> list() {
        return accountRepository.findAll();
    }

    public Account getOne(Long id) {

        List<Account> accounts = accountRepository.findAll();
        Account account = null;


        for(int i=0; i < accounts.size(); i++){
            if(accounts.get(i).getAccountNumber().equals(id)){
                return  accounts.get(i);
            }
        }
        return account;
    }

    public Account create(Account account) {
        accountRepository.save(account);
        return account;
    }

    @Override
    public void openSavingsAccount(Long accountId, Long amountToDeposit) {
        SavingsAccount acc = SavingsAccount.builder().accountNumber(accountId).type("savings").balance(Integer.parseInt(amountToDeposit.toString())).build();
        accountRepository.save(acc);
    }

    @Override
    public void openCurrentAccount(Long accountId) {
        CurrentAccount acc = CurrentAccount.builder().accountNumber(accountId).type("current").accountNumber(accountId).type("Current").balance(Integer.parseInt("0")).build();
        accountRepository.save(acc);
    }

    @Override
    public void withdraw(Long accountId, int amountToWithdraw) throws AccountNotFoundException, WithdrawalAmountTooLargeException {
        Account account = this.getOne(accountId);
        if (account != null && validWithdrawal(account, amountToWithdraw)){
            account.setBalance(account.getBalance()+ amountToWithdraw);
            accountRepository.save(account);
        } else {
            throw new WithdrawalAmountTooLargeException();
        }
    }

    private boolean validWithdrawal(Account account, int amountToWithdraw) {
        if(account.getType().equalsIgnoreCase("savings")){
            return account.getBalance() + amountToWithdraw >= 2000;
        } else {
            CurrentAccount ca = (CurrentAccount) account;
            return ca.getBalance() + amountToWithdraw > (ca.getBalance() + ca.getOverdraftLimit());
        }
    }

    @Override
    public void deposit(Long accountId, int amountToDeposit) throws AccountNotFoundException {
        Account account = this.getOne(accountId);
        if (account != null ){
            account.setBalance(account.getBalance()+ amountToDeposit);
            accountRepository.save(account);

        }
    }
}