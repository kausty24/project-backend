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
import com.app.dto.PlaceOrderDTO;
import com.app.service.IOrderService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class OrderController {
	@Autowired
	private IOrderService orderService;
	
	@PostMapping("/order/place")
	public ResponseEntity<?> registerCustomer(@RequestBody @Valid PlaceOrderDTO orderDetails) {
		return new ResponseEntity<>(orderService.placeOrder(orderDetails), HttpStatus.CREATED);
	}
}
