package com.app.service;

import java.util.Set;

import com.app.dto.CompletedOrderDTO;
import com.app.dto.OrderRequestDTO;
import com.app.dto.PlaceOrderDTO;
import com.app.entities.Order;

public interface IOrderService {

	Order placeOrder(PlaceOrderDTO orderDetails);

	Set<Order> getAllCompletedOrders(CompletedOrderDTO completedOrders);

	Set<Order> getAllPendingOrders(CompletedOrderDTO completedOrders);

	Set<Order> getAllOrderRequests(OrderRequestDTO completedOrders);

}
