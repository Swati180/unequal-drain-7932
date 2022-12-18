package com.treehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treehouse.exception.OrderException;
import com.treehouse.model.Orders;
import com.treehouse.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService os;
	
	@PostMapping("/add")
	public ResponseEntity<Orders> addOrder(@RequestBody Orders order)throws OrderException{
		return new ResponseEntity<Orders>(os.addOrder(order),HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Orders> updateOrder(@RequestBody Orders order)throws OrderException{
		return new ResponseEntity<Orders>(os.updateOrder(order),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{oid}")
	public ResponseEntity<Orders> deleteOrder(@PathVariable("oid") Integer orderid)throws OrderException{
		return new ResponseEntity<Orders>(os.deleteOrder(orderid),HttpStatus.OK);
	}
	
	@GetMapping("/view/{oid}")
	public ResponseEntity<Orders> viewOrder(@PathVariable("oid") Integer orderid)throws OrderException{
		return new ResponseEntity<Orders>(os.viewOrder(orderid),HttpStatus.OK);
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<Orders>> viewAllOrder()throws OrderException{
		return new ResponseEntity<List<Orders>>(os.viewAllOrders(),HttpStatus.OK);
	}
}
