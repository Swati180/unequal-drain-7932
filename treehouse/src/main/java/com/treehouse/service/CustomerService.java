package com.treehouse.service;

import com.treehouse.exception.CustomerExecption;
import com.treehouse.model.*;
import com.treehouse.model.DTO.CustomerDTO;
import com.treehouse.model.DTO.CustomerLoginDto;

import java.util.List;

public interface CustomerService {
    public Customer registerCustomer(CustomerDTO customer) throws CustomerExecption;

    public Customer updateCustomer(CustomerDTO customerDTO,Integer customerId) throws  CustomerExecption;
    public Customer deleteCustomer(Integer id) throws CustomerExecption;
    public List<Customer> getAllCustomer(String token) throws  CustomerExecption;
    public Customer getCustomerById(Integer id) throws CustomerExecption;
    public CustomerLogin loginCustomer(CustomerLoginDto customerLoginDto) throws  CustomerExecption;
    public CustomerLogin logoutCustomer(String key) throws CustomerExecption;
    public Bucket addPlantToBucket(Integer plantId,String key) throws CustomerExecption;
    public Bucket addPlanterToBucket(Planter planter, String key) throws CustomerExecption;
    public Bucket addSeedsToBucket(Integer seedsId,String key) throws CustomerExecption;
    public Bucket decreaseQuantityOfSeeds(Integer seedsID,String key) throws CustomerExecption;
    public Bucket decreaseQuantityOfPlant(Integer plantID,String key) throws CustomerExecption;
    public Bucket decreaseQuantityOfPlanter(Integer planterID,String key) throws CustomerExecption;


}
