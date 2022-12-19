package com.treehouse.controller;


import com.treehouse.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.treehouse.exception.AdminException;
import com.treehouse.model.Admin;
import com.treehouse.model.AdminLogin;
import com.treehouse.model.DTO.AdminDTO;
import com.treehouse.service.AdminService;



@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adservice;
	@Autowired
	private PlantService plantService;



	@PostMapping("/register")
	public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) throws AdminException {
		
		
		return new ResponseEntity<Admin>(adservice.registerAdmin(admin),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AdminLogin> loginAdmin(@RequestBody AdminDTO adminDto) throws AdminException {
		
		return new ResponseEntity<AdminLogin>(adservice.loginAdmin(adminDto),HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin, @PathVariable("id") Integer adminId) throws AdminException {
		
		return new ResponseEntity<Admin>(adservice.updateAdmin(admin),HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Admin> deleteAdmin(@RequestParam("key") String key) throws AdminException {
		
		return new ResponseEntity<Admin>(adservice.deleteAdmin(key),HttpStatus.OK);
		
		
	}


	// PLANT METHODS




	
	
	
	
	
	


}
