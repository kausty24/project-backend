package com.app.service;

import javax.validation.Valid;

import com.app.dto.PlaceOrderDTO;
import com.app.entities.Order;

public interface IOrderService  {

	Order placeOrder(PlaceOrderDTO orderDetails);

}
