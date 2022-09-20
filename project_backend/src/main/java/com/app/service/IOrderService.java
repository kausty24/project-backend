package com.app.service;

import java.util.Set;

import com.app.dto.CompletedOrderDTO;
import com.app.dto.DeleteBidDTO;
import com.app.dto.OrderRequestDTO;
import com.app.dto.PlaceBidDTO;
import com.app.dto.PlaceOrderDTO;
import com.app.entities.Bid;
import com.app.entities.Order;

public interface IOrderService {

	Order placeOrder(PlaceOrderDTO orderDetails);

	Set<Order> getAllCompletedOrders(CompletedOrderDTO completedOrders);

	Set<Order> getAllPendingOrders(CompletedOrderDTO completedOrders);

	Set<Order> getAllOrderRequests(OrderRequestDTO completedOrders);
	
	Order getOrderById(long orderId);
	
	void deleteOrderById(long orderId);
	
	Bid placeBid(PlaceBidDTO placeBidDTO);
	
	void deleteBid(DeleteBidDTO deleteBidDTO);
	
	void setOrderStatusCompleted(Double rating,long orderId);
	
	Order finalizeBid(PlaceBidDTO finalizeBidDTO);
	
	Set<Bid> getAllBidsByOrder(long orderId);
}
