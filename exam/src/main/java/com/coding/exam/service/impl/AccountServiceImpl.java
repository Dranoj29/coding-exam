package com.coding.exam.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.exam.dto.AccountDTO;
import com.coding.exam.entity.Account;
import com.coding.exam.entity.Customer;
import com.coding.exam.repository.AccountRepo;
import com.coding.exam.repository.CustomerRepo;
import com.coding.exam.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private AccountRepo accountRepo;

	@Override
	public Customer saveAccount(AccountDTO accountDto) {
		Account account = new Account();
		account.setAccountNumber(this.randomNumber(1000000, 1999999));//just assigning random number hence nothing is said about how it will be generated
		account.setAccountType(accountDto.getAccountType().getValue());
		account.setAvailableBalance("100.00"); //just setting the balance for inquiry purposes
		this.accountRepo.save(account);
		
		Customer costumer = new Customer();
		costumer.setCustomerNumber(this.randomNumber(100000, 199999));//just assigning random number hence nothing is said about how it will be generated
		costumer.setCustomerName(accountDto.getCustomerName());
		costumer.setCustomerMobile(accountDto.getCustomerMobile());
		costumer.setCustomerEmail(accountDto.getCustomerEmail());
		costumer.setAddress1(accountDto.getAddress1());
		costumer.setAddress2(accountDto.getAddress2());
		costumer.getAccounts().add(account);

		return this.customerRepo.save(costumer);	
	}
	
	@Override
	public Optional<Customer> getCustomerAccount(int customerNumber) {
		return this.customerRepo.findByCustomerNumber(customerNumber);
	}
	
	private int randomNumber(int min, int max) {
        Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
}
