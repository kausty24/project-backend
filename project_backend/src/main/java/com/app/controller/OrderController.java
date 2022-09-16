package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CompletedOrderDTO;
import com.app.dto.OrderRequestDTO;
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

	@PostMapping("/order/completed")
	public ResponseEntity<?> getAllCompletedOrders(@RequestBody @Valid CompletedOrderDTO completedOrderDTO) {
		return new ResponseEntity<>(orderService.getAllCompletedOrders(completedOrderDTO), HttpStatus.OK);
	}
	
	@PostMapping("/order/pending")
	public ResponseEntity<?> getAllPendingOrders(@RequestBody @Valid CompletedOrderDTO completedOrderDTO) {
		return new ResponseEntity<>(orderService.getAllPendingOrders(completedOrderDTO), HttpStatus.OK);
	}
	
	@PostMapping("/order/request")
	public ResponseEntity<?> getAllOrderRequests(@RequestBody @Valid OrderRequestDTO orderRequestDTO) {
		return new ResponseEntity<>(orderService.getAllOrderRequests(orderRequestDTO), HttpStatus.OK);
	}

}
