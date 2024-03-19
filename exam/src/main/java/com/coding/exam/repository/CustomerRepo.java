package com.coding.exam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.exam.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{

	Optional<Customer> findByCustomerNumber(int customerNumber);
}
