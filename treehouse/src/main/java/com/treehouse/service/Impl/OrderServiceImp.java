package com.treehouse.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treehouse.Repo.OrderRepo;
import com.treehouse.exception.OrderException;
import com.treehouse.model.Orders;
import com.treehouse.service.OrderService;

@Service
public class OrderServiceImp implements OrderService{

	@Autowired
	private OrderRepo or;
	
	
	@Override
	public Orders addOrder(Orders order) throws OrderException {
		return or.save(order);
	}

	@Override
	public Orders updateOrder(Orders order) throws OrderException {
		Optional<Orders> opt=or.findById(order.getOrderId());
		if(opt.isPresent()) {
			return or.save(order);
		}
		else throw new OrderException("Order not found with orderId :"+order.getOrderId());
	}

	@Override
	public Orders deleteOrder(Integer orderId) throws OrderException {
		Optional<Orders> opt=or.findById(orderId);
		if(opt.isPresent()) {
			Orders od=opt.get();
			or.delete(od);
			return od;
		}
		else throw new OrderException("Order not found with orderId :"+orderId);

	}

	@Override
	public Orders viewOrder(Integer orderId) throws OrderException {
		
		return or.findById(orderId).orElseThrow(()->new OrderException("Order not found with orderId :"+orderId));
	}

	@Override
	public List<Orders> viewAllOrders() throws OrderException {
		List<Orders> orders=or.findAll();
		if(orders.size()>0)return orders;
		else throw new OrderException("Order not founds");
	}

}
