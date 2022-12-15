package com.treehouse.service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.treehouse.Repo.*;
import com.treehouse.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treehouse.exception.CustomerExecption;
import com.treehouse.model.DTO.CustomerDTO;
import com.treehouse.model.DTO.CustomerLoginDto;
import com.treehouse.service.CustomerService;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerLoginRepo customerLoginRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private AdminLoginRepo adminLoginRepo;
    @Autowired
    private PlantRepo plantRepo;
    @Autowired
    private BucketRepo bucketRepo;
    @Override
    public Customer registerCustomer(CustomerDTO customer) throws CustomerExecption {

        Customer customer1 = new Customer();
        customer1.setEmail(customer.getEmail());
        customer1.setMobile(customer.getMobile());
        customer1.setFirstname(customer.getFirstname());
        customer1.setLastName(customer.getLastName());
        customer1.setPassword(customer.getPassword());

        return customerRepo.save(customer1);
    }

    @Override
    public Customer updateCustomer(CustomerDTO customerDTO, Integer customerId) throws CustomerExecption {



        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        if(customerOptional.isPresent()){
            Customer customer2 = new Customer();
            customer2.setCustomerId(customerId);
            customer2.setPassword(customerDTO.getPassword());
            customer2.setLastName(customerDTO.getLastName());
            customer2.setFirstname(customerDTO.getFirstname());
            customer2.setEmail(customerDTO.getEmail());
            customer2.setBucket(customerOptional.get().getBucket());

            return customerRepo.save(customer2);

        }

        throw new CustomerExecption("ID is not Valid");


    }

    @Override
    public Customer deleteCustomer(Integer id) throws CustomerExecption {


            Optional<Customer> customerOptional = customerRepo.findById(id);

            if(customerOptional.isPresent()){
                customerRepo.delete(customerOptional.get());
                Optional<CustomerLogin> customerLogin=customerLoginRepo.findById(id);
               if(customerLogin.isPresent()){

                   customerLoginRepo.delete(customerLogin.get());
                   return customerOptional.get();
               }

                return customerOptional.get();
            }
            throw new CustomerExecption("Pass the valid ID");


    }

    @Override
    public List<Customer> getAllCustomer(String token) throws CustomerExecption {

        AdminLogin al = adminLoginRepo.findByKey(token);
        if(al!=null){
            return customerRepo.findAll();
        }
        throw new CustomerExecption("You are not Authorized");
    }

    @Override
    public Customer getCustomerById(Integer id) throws CustomerExecption {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if(customerOptional.isPresent()){
            return customerOptional.get();
        }
        throw new CustomerExecption("valid not found");
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

    @Override
    public CustomerLogin logoutCustomer(String key) throws CustomerExecption {
        CustomerLogin customerLogin=customerLoginRepo.findByKey(key);
        if(customerLogin!=null){
            customerLoginRepo.delete(customerLogin);
            return customerLogin;
        }
        throw new CustomerExecption("please enter Valid key");
    }

    @Override
    public Bucket addPlantToBucket(Integer plantId, String key) throws CustomerExecption {

        CustomerLogin customerLogin =  this.checkPermission(key);
        Optional<Plant> optionalPlant = plantRepo.findById(plantId);
        if(customerLogin != null && optionalPlant.isPresent()){
          Optional<Customer> optionalCustomer1 = customerRepo.findById(customerLogin.getCustomerId());
          Bucket bucket = optionalCustomer1.get().getBucket();
          Plant plant= optionalPlant.get();
          boolean flag=false;
          if(bucket != null){
              plant.setPlantQuantity(1);
              for(Plant i:bucket.getPlants()){
                  if(i.getPlantId()==plantId){
                      flag=true;
                  }
              }
              if(flag){
                  bucket.setPlantQuantity(bucket.getPlantQuantity()+1);
                  bucket.setTotalItems(bucket.getTotalItems()+1);
                  bucket.setTotalPrice(bucket.getTotalPrice()+plant.getPlantCost());
                  optionalCustomer1.get().setBucket(bucket);
                  return customerRepo.save(optionalCustomer1.get()).getBucket();
              }
              else{
                  bucket.getPlants().add(plant);
                  bucket.setPlantQuantity(bucket.getPlantQuantity()+1);
                  bucket.setTotalItems(bucket.getTotalItems()+1);
                  bucket.setTotalPrice(bucket.getTotalPrice()+plant.getPlantCost());
                  optionalCustomer1.get().setBucket(bucket);
                  return customerRepo.save(optionalCustomer1.get()).getBucket();
              }



          }else{
              Bucket bucket1 = new Bucket();
              bucket1.getPlants().add(plant);
              bucket1.setPlantQuantity(1);
              bucket1.setTotalItems(1);
              bucket1.setTotalPrice(plant.getPlantCost());
              bucket1.setPlantPrice(plant.getPlantCost());
              optionalCustomer1.get().setBucket(bucket1);
              return customerRepo.save(optionalCustomer1.get()).getBucket();


          }
        }

        throw new CustomerExecption("Either Your Login Key or Plant detail is not correct");
    }

    @Override
    public Bucket addPlanterToBucket(Planter planter, String key) throws CustomerExecption {
        return null;
    }

    @Override
    public Bucket addSeedsToBucket(Seeds seeds, String key) throws CustomerExecption {
        return null;
    }

    // checkPermission Method
    public CustomerLogin checkPermission(String key){
        return customerLoginRepo.findByKey(key);

    }
}
