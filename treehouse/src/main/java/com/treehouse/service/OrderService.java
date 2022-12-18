package com.treehouse.service;

import java.util.List;

import com.treehouse.exception.OrderException;
import com.treehouse.model.Orders;

public interface OrderService {

	public Orders addOrder(Orders order)throws OrderException;
	
	public Orders updateOrder(Orders order)throws OrderException;
	
	public Orders deleteOrder(Integer orderId)throws OrderException;
	
	public Orders viewOrder(Integer orderId)throws OrderException;
	
	public List<Orders> viewAllOrders()throws OrderException;
	
	
}
