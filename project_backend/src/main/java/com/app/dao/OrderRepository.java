package com.app.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Order;
import com.app.entities.OrderStatus;
import com.app.entities.Service;
import com.app.entities.Vendor;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	public Set<Order> findByOrderStatusAndFinalVendorAndService(OrderStatus orderStatus, Vendor vendor, Service service);
	public Set<Order> findByOrderStatusAndServiceAndCustomerCity(OrderStatus orderStatus, Service service, String city);
}
