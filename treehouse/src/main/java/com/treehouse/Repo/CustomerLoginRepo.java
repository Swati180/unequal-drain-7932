package com.treehouse.Repo;

import com.treehouse.model.CustomerLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerLoginRepo extends JpaRepository<CustomerLogin,Integer> {
    public CustomerLogin findByKey(String key);
}
