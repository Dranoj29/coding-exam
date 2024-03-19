package com.coding.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.exam.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{

}
