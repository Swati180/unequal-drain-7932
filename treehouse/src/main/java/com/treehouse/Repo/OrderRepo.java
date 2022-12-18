package com.treehouse.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treehouse.model.Orders;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {

}
