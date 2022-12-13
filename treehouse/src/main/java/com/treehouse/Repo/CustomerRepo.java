package com.treehouse.Repo;

import com.treehouse.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    public Customer findByEmailAndPassword(String username ,String Password);

}
