package com.app.service;

import java.time.LocalDateTime;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.CustomerRepository;
import com.app.dao.OrderRepository;
import com.app.dao.OrderStatusRepository;
import com.app.dao.ServiceRepository;
import com.app.dao.VendorRepository;
import com.app.dto.CompletedOrderDTO;
import com.app.dto.OrderRequestDTO;
import com.app.dto.PlaceOrderDTO;
import com.app.entities.Order;
import com.app.entities.OrderStatus;
import com.app.entities.OrderStatusType;
import com.app.entities.Vendor;


@Service
@Transactional
public class OrderServices implements IOrderService {

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private CustomerRepository custRepo;
	@Autowired
	private ServiceRepository serviceRepo;
	@Autowired
	private OrderStatusRepository orderStatusRepo;
	@Autowired
	private VendorRepository vendorRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Order placeOrder(@Valid PlaceOrderDTO orderDetails) {
		Order transientOrder = mapper.map(orderDetails, Order.class);
		transientOrder.setCustomer(custRepo.findById(orderDetails.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException("Customer Not Found")));
		transientOrder.setService(serviceRepo.findByServiceType(orderDetails.getServiceType())
				.orElseThrow(() -> new ResourceNotFoundException("Service Not Found")));
		transientOrder.setOrderPlacedTime(LocalDateTime.now());
		transientOrder.setOrderStatus(orderStatusRepo.findByOrderStatusType(OrderStatusType.NEW));
		Order persistentOrder = orderRepo.save(transientOrder);
		return persistentOrder;
	}

	@Override
	public Set<Order> getAllCompletedOrders(@Valid CompletedOrderDTO completedOrders) {
		Vendor vendor = vendorRepo.findById(completedOrders.getVendorId()).orElseThrow(()-> new ResourceNotFoundException("Vendor not Found"));
		com.app.entities.Service service = serviceRepo.findByServiceType(completedOrders.getServiceType()).orElseThrow(()-> new ResourceNotFoundException("Service Not Found"));
		OrderStatus orderStatus = orderStatusRepo.findByOrderStatusType(OrderStatusType.COMPLETED);
		return orderRepo.findByOrderStatusAndFinalVendorAndService(orderStatus, vendor, service);
	}

	@Override
	public Set<Order> getAllPendingOrders(CompletedOrderDTO completedOrders) {
		Vendor vendor = vendorRepo.findById(completedOrders.getVendorId()).orElseThrow(()-> new ResourceNotFoundException("Vendor not Found"));
		com.app.entities.Service service = serviceRepo.findByServiceType(completedOrders.getServiceType()).orElseThrow(()-> new ResourceNotFoundException("Service Not Found"));
		OrderStatus orderStatus = orderStatusRepo.findByOrderStatusType(OrderStatusType.PENDING);
		return orderRepo.findByOrderStatusAndFinalVendorAndService(orderStatus, vendor, service);
	}

	@Override
	public Set<Order> getAllOrderRequests(OrderRequestDTO orderRequestDTO) {
		OrderStatus orderStatus = orderStatusRepo.findByOrderStatusType(OrderStatusType.NEW);
		com.app.entities.Service service = serviceRepo.findByServiceType(orderRequestDTO.getServiceType()).orElseThrow(()-> new ResourceNotFoundException("Service Not Found"));
		return orderRepo.findByOrderStatusAndServiceAndCustomerCity(orderStatus, service, orderRequestDTO.getCity());
	}

}
