package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CustomerRegistrationDTO;
import com.app.dto.CustomerUpdateDTO;
import com.app.dto.FindContact;
import com.app.dto.FindEmail;
import com.app.dto.LoginRequest;
import com.app.entities.OrderStatusType;
import com.app.service.ICustomerService;
import com.app.service.IOrderService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class CustomerController {
	@Autowired
	private ICustomerService custService;
	
	@Autowired
	private IOrderService orderService;
	
	@PostMapping("/reg/customer")
	public ResponseEntity<?> registerCustomer(@RequestBody @Valid CustomerRegistrationDTO customerDetails) {
		return new ResponseEntity<>(custService.addNewCustomer(customerDetails), HttpStatus.CREATED);
	}
	
	@PostMapping("/edit/customer")
	public ResponseEntity<?> updateCustomer(@RequestBody @Valid CustomerUpdateDTO updateDetails){
		return new ResponseEntity<>(custService.updateCustomerDetail(updateDetails),HttpStatus.CREATED);
	}
	
	@PostMapping("/login/customer")
	public ResponseEntity<?> loginCustomer(@RequestBody @Valid LoginRequest loginCredentials) {
		return new ResponseEntity<>(custService.authenticateCustomer(loginCredentials), HttpStatus.OK);
	}
	
	@PostMapping("/edit/customer/emailExist")
	public ResponseEntity<?> searchEmail(@RequestBody @Valid FindEmail emailId){
		return new ResponseEntity<>(custService.findEmailId(emailId),HttpStatus.OK);
	}
	
	@PostMapping("/edit/customer/contactExists")
	public ResponseEntity<?> searchContact(@RequestBody @Valid FindContact contactNo){
		return new ResponseEntity<>(custService.findContactNo(contactNo),HttpStatus.OK);
	}
	
	@GetMapping("/order/active/{customerId}")
	public ResponseEntity<?> findActiveOrdersByCustomer(@PathVariable long customerId) {
		return new ResponseEntity<>(orderService.findOrdersByCustomerAndStatus(customerId,OrderStatusType.NEW),HttpStatus.OK);
	}
	
	@GetMapping("/order/pending/{customerId}")
	public ResponseEntity<?> findPendingOrdersByCustomer(@PathVariable long customerId) {
		return new ResponseEntity<>(orderService.findOrdersByCustomerAndStatus(customerId,OrderStatusType.PENDING),HttpStatus.OK);
	}
	
	@GetMapping("/order/completed/{customerId}")
	public ResponseEntity<?> findCompletedOrdersByCustomer(@PathVariable long customerId) {
		return new ResponseEntity<>(orderService.findOrdersByCustomerAndStatus(customerId,OrderStatusType.COMPLETED),HttpStatus.OK);
	}
}
