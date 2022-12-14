package com.treehouse.service;

import org.springframework.stereotype.Service;

import com.treehouse.exception.AdminException;
import com.treehouse.model.Admin;
import com.treehouse.model.AdminLogin;
import com.treehouse.model.DTO.AdminDTO;


public interface AdminService {

	public Admin registerAdmin(Admin admin) throws AdminException;
	
	public AdminLogin loginAdmin(AdminDTO adminDto) throws AdminException;
	
	public Admin updateAdmin(Admin admin) throws AdminException;
	
	public Admin deleteAdmin(String key) throws AdminException;
	
	
	
}
