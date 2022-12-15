package com.treehouse.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treehouse.model.Seeds;

@Repository
public interface SeedRepo extends JpaRepository<Seeds,Integer>{

}
