package com.treehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.treehouse.exception.AdminException;
import com.treehouse.model.Admin;
import com.treehouse.model.AdminLogin;
import com.treehouse.model.DTO.AdminDTO;
import com.treehouse.service.AdminService;



@RestController
public class AdminController {

	@Autowired
	private AdminService adservice;
	
	@PostMapping("/admins/register")
	public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) throws AdminException {
		
		
		return new ResponseEntity<Admin>(adservice.registerAdmin(admin),HttpStatus.CREATED);
	}
	
	@PostMapping("/admins/login")
	public ResponseEntity<AdminLogin> loginAdmin(@RequestBody AdminDTO adminDto) throws AdminException {
		
		return new ResponseEntity<AdminLogin>(adservice.loginAdmin(adminDto),HttpStatus.OK);
	}
	
	@PutMapping("admins/update/{id}")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin, @PathVariable("id") Integer adminId) throws AdminException {
		
		return new ResponseEntity<Admin>(adservice.updateAdmin(admin),HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("admins/delete")
	public ResponseEntity<Admin> deleteAdmin(@RequestParam("key") String key) throws AdminException {
		
		return new ResponseEntity<Admin>(adservice.deleteAdmin(key),HttpStatus.OK);
		
		
	}
	
	
	
	
	
	


}
