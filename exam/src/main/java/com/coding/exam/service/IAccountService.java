package com.coding.exam.service;


import java.util.Optional;

import com.coding.exam.dto.AccountDTO;
import com.coding.exam.entity.Customer;

public interface IAccountService {

	Customer saveAccount(AccountDTO accountDto);

	Optional<Customer> getCustomerAccount(int customerNumber);

}
