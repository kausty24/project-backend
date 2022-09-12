package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CustomerRepository;
import com.app.dto.CustomerRegistrationDTO;
import com.app.entities.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Customer addNewCustomer(CustomerRegistrationDTO newCustomer) {
		Customer transientCustomer = mapper.map(newCustomer, Customer.class);
		Customer persistentCustomer = custRepo.save(transientCustomer);
		return persistentCustomer;
	}

}
