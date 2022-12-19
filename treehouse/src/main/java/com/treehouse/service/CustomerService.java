package com.treehouse.service;

import com.treehouse.exception.CustomerException;
import com.treehouse.model.*;
import com.treehouse.model.DTO.CustomerDTO;
import com.treehouse.model.DTO.CustomerLoginDto;

import java.util.List;

public interface CustomerService {
    public Customer registerCustomer(CustomerDTO customer) throws CustomerException;

    public Customer updateCustomer(CustomerDTO customerDTO,Integer customerId) throws CustomerException;
    public Customer deleteCustomer(Integer id) throws CustomerException;
    public List<Customer> getAllCustomer(String token) throws CustomerException;
    public Customer getCustomerById(Integer id) throws CustomerException;
    public CustomerLogin loginCustomer(CustomerLoginDto customerLoginDto) throws CustomerException;
    public CustomerLogin logoutCustomer(String key) throws CustomerException;
    public Bucket addPlantToBucket(Integer plantId,String key) throws CustomerException;
    public Bucket addPlanterToBucket(Integer planterId, String key) throws CustomerException;
    public Bucket addSeedsToBucket(Integer seedsId,String key) throws CustomerException;
    public Bucket decreaseQuantityOfSeeds(Integer seedsID,String key) throws CustomerException;
    public Bucket decreaseQuantityOfPlant(Integer plantID,String key) throws CustomerException;
    public Bucket decreaseQuantityOfPlanter(Integer planterID,String key) throws CustomerException;



}
