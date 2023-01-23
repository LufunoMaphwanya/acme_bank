package com.acme.test01.lufunomaphwanya.acme_bank_demo.repositories;

import com.acme.test01.lufunomaphwanya.acme_bank_demo.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountNumber(Long accountNumber);


}