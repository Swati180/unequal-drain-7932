package com.treehouse.service.Impl;

import com.treehouse.Repo.CustomerLoginRepo;
import com.treehouse.Repo.CustomerRepo;
import com.treehouse.exception.CustomerExecption;
import com.treehouse.model.Customer;
import com.treehouse.model.CustomerLogin;
import com.treehouse.model.DTO.CustomerDTO;
import com.treehouse.model.DTO.CustomerLoginDto;
import com.treehouse.service.CustomerService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerLoginRepo customerLoginRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public Customer registerCustomer(CustomerDTO customer) throws CustomerExecption {

        Customer customer1 = new Customer();
        customer1.setEmail(customer.getEmail());
        customer1.setMobile(customer.getMobile());
        customer1.setName(customer.getFirstname());
        customer1.setLastName(customer.getLastName());
        customer1.setPassword(customer.getPassword());

        return customerRepo.save(customer1);
    }

    @Override
    public Customer updateCustomer(CustomerDTO customerDTO, Integer customerId) throws CustomerExecption {
        return null;
    }

    @Override
    public Customer deleteCustomer(Integer id) throws CustomerExecption {
        return null;
    }

    @Override
    public List<Customer> getAllCustomer() throws CustomerExecption {
        return null;
    }

    @Override
    public Customer getCustomerById(Integer id) throws CustomerExecption {
        return null;
    }

    @Override
    public CustomerLogin loginCustomer(CustomerLoginDto customerLoginDto) throws CustomerExecption {

        Customer customer = customerRepo.findByEmailAndPassword(customerLoginDto.getEmail(), customerLoginDto.getPassword());
        if(customer != null){
            CustomerLogin customerLogin = new CustomerLogin();
            customerLogin.setLocalDate(LocalDate.now());
            String key = RandomString.make(6);
            customerLogin.setKey(key);
            customerLogin.setCustomerId(customer.getCustomerId());
            return customerLoginRepo.save(customerLogin);
        }
        throw new CustomerExecption ("Details Entered Wrong");


    }
}
