package com.app.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.CustomerRepository;
import com.app.dao.OrderRepository;
import com.app.dto.PlaceOrderDTO;
import com.app.entities.Order;

@Service
@Transactional
public class OrderServices implements IOrderService {

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Order placeOrder(@Valid PlaceOrderDTO orderDetails) {
		Order transientOrder = mapper.map(orderDetails, Order.class);
		transientOrder.setCustomer(custRepo.findById(orderDetails.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException("Customer Not Found")));
		Order persistentOrder = orderRepo.save(transientOrder);
		return persistentOrder;
	}

}
