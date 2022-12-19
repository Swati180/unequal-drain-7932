package com.treehouse.controller;

import com.treehouse.exception.AdminException;
import com.treehouse.exception.CustomerException;
import com.treehouse.model.Bucket;
import com.treehouse.model.Customer;
import com.treehouse.model.CustomerLogin;
import com.treehouse.model.DTO.CustomerDTO;
import com.treehouse.model.DTO.CustomerLoginDto;
import com.treehouse.model.Message;
import com.treehouse.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer (@Valid @RequestBody CustomerDTO customerDTO) throws CustomerException {
        return new ResponseEntity<Customer>(customerService.registerCustomer(customerDTO),HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<CustomerLogin> loginCustomer(@Valid @RequestBody CustomerLoginDto customerLoginDto) throws CustomerException {
        return new ResponseEntity<>(customerService.loginCustomer(customerLoginDto),HttpStatus.OK);
    }
   @GetMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Integer id) throws CustomerException {
        return new  ResponseEntity<Customer>(customerService.getCustomerById(id),HttpStatus.OK);
   }
   @GetMapping("/admin/all")
    public ResponseEntity<List<Customer>> getAllCustomer(@RequestParam("token") String token) throws CustomerException {
        return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(token),HttpStatus.OK);
   }
   @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable("id") Integer id) throws CustomerException {
        return new ResponseEntity<Customer>(customerService.deleteCustomer(id),HttpStatus.OK);
   }
   @PostMapping("/logout/{key}")
    public ResponseEntity<CustomerLogin> logoutCustomer(@PathVariable("key") String key) throws CustomerException {
        return new ResponseEntity<CustomerLogin>(customerService.logoutCustomer(key),HttpStatus.OK);
   }

   // PLANT

   @PostMapping("/BuyPlant/{key}/{pid}")
    public ResponseEntity<Bucket> addPlantToCart(@PathVariable("key") String key, @PathVariable("pid") Integer pid) throws CustomerException {
        return new ResponseEntity<Bucket>(customerService.addPlantToBucket(pid,key),HttpStatus.CREATED);
   }
    /*        SEEDS            */

    @PostMapping("/BuySeeds/{key}/{Sid}")
    public ResponseEntity<Bucket> addSeedsToCart(@PathVariable("key") String key, @PathVariable("Sid") Integer Sid) throws CustomerException {
        return new ResponseEntity<Bucket>(customerService.addSeedsToBucket(Sid,key),HttpStatus.CREATED);
    }

    @PostMapping("/BuyPlanters/{key}/{PLid}")
    public ResponseEntity<Bucket> addPlantersToCart(@PathVariable("key") String key, @PathVariable("PLid") Integer PLid) throws CustomerException {
        return new ResponseEntity<Bucket>(customerService.addPlanterToBucket(PLid,key),HttpStatus.CREATED);
    }

    @DeleteMapping("/DecreaseSeedsQuantity/{key}/{Sid}")
    public ResponseEntity<Bucket> decreaseQuantityOfSeeds(@PathVariable("key") String key, @PathVariable("Sid") Integer Sid) throws CustomerException {
        return new ResponseEntity<Bucket>(customerService.decreaseQuantityOfSeeds(Sid,key),HttpStatus.CREATED);
    }

    @DeleteMapping("/DecreasePlantsQuantity/{key}/{Pid}")
    public ResponseEntity<Bucket> decreaseQuantityOfPlants(@PathVariable("key") String key, @PathVariable("Pid") Integer Pid) throws CustomerException {
        return new ResponseEntity<Bucket>(customerService.decreaseQuantityOfPlant(Pid,key),HttpStatus.CREATED);
    }

    @DeleteMapping("/DecreasePlantersQuantity/{key}/{PLid}")
    public ResponseEntity<Bucket> decreaseQuantityOfPlanters(@PathVariable("key") String key, @PathVariable("PLid") Integer PLid) throws CustomerException {
        return new ResponseEntity<Bucket>(customerService.decreaseQuantityOfPlanter(PLid,key),HttpStatus.CREATED);
    }



}
