package com.app.service;

import com.app.dto.CustomerRegistrationDTO;
import com.app.entities.Customer;

public interface ICustomerService {
	public Customer addNewCustomer(CustomerRegistrationDTO newCustomer);
}
