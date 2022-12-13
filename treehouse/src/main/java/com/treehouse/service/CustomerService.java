package com.treehouse.service;

import com.treehouse.exception.CustomerExecption;
import com.treehouse.model.Customer;
import com.treehouse.model.CustomerLogin;
import com.treehouse.model.DTO.CustomerDTO;
import com.treehouse.model.DTO.CustomerLoginDto;

import java.util.List;

public interface CustomerService {
    public Customer registerCustomer(CustomerDTO customer) throws CustomerExecption;

    public Customer updateCustomer(CustomerDTO customerDTO,Integer customerId) throws  CustomerExecption;
    public Customer deleteCustomer(Integer id) throws CustomerExecption;
    public List<Customer> getAllCustomer() throws  CustomerExecption;
    public Customer getCustomerById(Integer id) throws CustomerExecption;
    public CustomerLogin loginCustomer(CustomerLoginDto customerLoginDto) throws  CustomerExecption;


}
