package com.coding.exam.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.exam.dto.AccountDTO;
import com.coding.exam.entity.Customer;
import com.coding.exam.service.IAccountService;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
	
	@Autowired
	private IAccountService accountService;

	
	@PostMapping("")
	public ResponseEntity<Map<String, Object>> saveAccount(@RequestBody AccountDTO accountDTO){
		Map<String, Object> responseBody = new LinkedHashMap<String, Object>();
		String validationMessage = fieldValidation(accountDTO);
		if(validationMessage != null) {
			responseBody.put("transactionStatusCode", HttpStatus.BAD_REQUEST.value());
			responseBody.put("transactionStatusDescription", validationMessage);
			return ResponseEntity.badRequest().body(responseBody);
		}
		
		Customer custumer = this.accountService.saveAccount(accountDTO);
		responseBody.put("customerNumber", custumer.getCustomerNumber());
		responseBody.put("transactionStatusCode", HttpStatus.CREATED.value());
		responseBody.put("transactionStatusDescription", "Customer account created");
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(responseBody);
	}
	
	@GetMapping("/{customerNumber}")
	public ResponseEntity<Map<String, Object>> getCustomerAccount(@PathVariable int customerNumber){
		Optional<Customer> result = this.accountService.getCustomerAccount(customerNumber);
		Map<String, Object> responseBody = new LinkedHashMap<String, Object>();
		if(result.isPresent()) {
			Customer customer = result.get();
			responseBody.put("cutomerNumber", customer.getCustomerNumber()); 
			responseBody.put("customerName", customer.getCustomerName()); 
			responseBody.put("customerMobile", customer.getCustomerMobile()); 
			responseBody.put("customerEmail", customer.getCustomerEmail()); 
			responseBody.put("customerAddress1", customer.getAddress1()); 
			responseBody.put("customerAddress2", customer.getAddress2());
			responseBody.put("savings", customer.getAccounts());
			responseBody.put("transactionStatusCode", HttpStatus.FOUND.value()); 
			responseBody.put("transactionStatusDescription", "Customer Account found");
			return ResponseEntity.status(HttpStatus.FOUND.value()).body(responseBody);
		}
		responseBody.put("transactionStatusCode", HttpStatus.NOT_FOUND.value()); 
		responseBody.put("transactionStatusDescription", "Customer not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(responseBody); //I just used correct status code for not fount instead of 401 unauthorized status code
	}
	
	private String fieldValidation(AccountDTO accountDTO) {
		if(accountDTO.getCustomerName() == null || accountDTO.getCustomerName().isEmpty()) {
			return "Name is required field";
		}
		
		if(accountDTO.getCustomerMobile() == null || accountDTO.getCustomerMobile().isEmpty()) {
			return "Mobile is required field";
		}
		
		if(!Pattern.compile("[0-9]+").matcher(accountDTO.getCustomerMobile()).matches()) {
			return "Mobile is Invalid";
		}
		
		if(accountDTO.getCustomerEmail() == null || accountDTO.getCustomerEmail().isEmpty()) {
			return "Email is required field";
		}
		
		if(!Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(accountDTO.getCustomerEmail()).matches()) {
			return "Email is Invalid";
		}
		
		if(accountDTO.getAddress1() == null || accountDTO.getAddress1().isEmpty()) {
			return "Address1 is required field";
		}
		
		return null;
	}
	
}
