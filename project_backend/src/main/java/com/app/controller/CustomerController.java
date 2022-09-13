package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CustomerRegistrationDTO;
import com.app.dto.LoginRequest;
import com.app.service.ICustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class CustomerController {
	@Autowired
	private ICustomerService custService;
	
	@PostMapping("/reg/customer")
	public ResponseEntity<?> registerCustomer(@RequestBody @Valid CustomerRegistrationDTO customerDetails) {
		return new ResponseEntity<>(custService.addNewCustomer(customerDetails), HttpStatus.CREATED);
	}
	
	@PostMapping("/login/customer")
	public ResponseEntity<?> loginCustomer(@RequestBody @Valid LoginRequest loginCredentials) {
		return new ResponseEntity<>(custService.authenticateCustomer(loginCredentials), HttpStatus.OK);
	}
}
