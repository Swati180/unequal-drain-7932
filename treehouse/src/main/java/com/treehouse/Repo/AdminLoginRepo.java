package com.treehouse.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treehouse.model.AdminLogin;

@Repository
public interface AdminLoginRepo extends JpaRepository<AdminLogin, Integer> {
	
	public AdminLogin findByKey(String key);

	
	
}
