package com.treehouse.controller;

import com.treehouse.exception.CustomerExecption;
import com.treehouse.model.Customer;
import com.treehouse.model.CustomerLogin;
import com.treehouse.model.DTO.CustomerDTO;
import com.treehouse.model.DTO.CustomerLoginDto;
import com.treehouse.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @PostMapping("/customer/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerExecption {
        return new ResponseEntity<Customer>(customerService.registerCustomer(customerDTO),HttpStatus.CREATED);
    }
    @PostMapping("/cusotmer/login")
    public ResponseEntity<CustomerLogin> loginCustomer(@RequestBody CustomerLoginDto customerLoginDto) throws CustomerExecption {
        return new ResponseEntity<>(customerService.loginCustomer(customerLoginDto),HttpStatus.OK);
    }

}
