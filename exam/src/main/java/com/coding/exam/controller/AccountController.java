package com.coding.exam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

	
	@PostMapping("")
	public ResponseEntity<Object> createAccount(){
		
		return ResponseEntity.ok("test");
	}
	
	@PostMapping("/{customerId}")
	public ResponseEntity<Object> getAccountDetails(){
		
		return ResponseEntity.ok("test");
	}
	
}
