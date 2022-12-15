package com.treehouse.controller;

import com.treehouse.exception.CustomerExecption;
import com.treehouse.model.Bucket;
import com.treehouse.model.Customer;
import com.treehouse.model.CustomerLogin;
import com.treehouse.model.DTO.CustomerDTO;
import com.treehouse.model.DTO.CustomerLoginDto;
import com.treehouse.model.Plant;
import com.treehouse.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerExecption {
        return new ResponseEntity<Customer>(customerService.registerCustomer(customerDTO),HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<CustomerLogin> loginCustomer(@RequestBody CustomerLoginDto customerLoginDto) throws CustomerExecption {
        return new ResponseEntity<>(customerService.loginCustomer(customerLoginDto),HttpStatus.OK);
    }
   @GetMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Integer id) throws CustomerExecption {
        return new  ResponseEntity<Customer>(customerService.getCustomerById(id),HttpStatus.OK);
   }
   @GetMapping("/admin/all")
    public ResponseEntity<List<Customer>> getAllCustomer(@RequestParam("token") String token) throws CustomerExecption {
        return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(token),HttpStatus.OK);
   }
   @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable("id") Integer id) throws CustomerExecption {
        return new ResponseEntity<Customer>(customerService.deleteCustomer(id),HttpStatus.OK);
   }
   @PostMapping("/logout/{key}")
    public ResponseEntity<CustomerLogin> logoutCustomer(@PathVariable("key") String key) throws CustomerExecption {
        return new ResponseEntity<CustomerLogin>(customerService.logoutCustomer(key),HttpStatus.OK);
   }

   @PostMapping("/BuyPlant/{key}/{pid}")
    public ResponseEntity<Bucket> addPlantToCart(@PathVariable("key") String key, @PathVariable("pid") Integer pid) throws CustomerExecption{
        return new ResponseEntity<Bucket>(customerService.addPlantToBucket(pid,key),HttpStatus.CREATED);
   }


}
