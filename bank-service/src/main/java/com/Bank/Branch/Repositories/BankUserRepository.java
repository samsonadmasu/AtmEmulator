package com.Bank.Branch.Repositories;

import com.Bank.Branch.Models.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, Long> {
     BankUser  findByUsername(String username);
}
