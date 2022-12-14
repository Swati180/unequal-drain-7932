package com.treehouse.service.Impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treehouse.Repo.AdminLoginRepo;
import com.treehouse.Repo.AdminRepo;
import com.treehouse.exception.AdminException;
import com.treehouse.model.Admin;
import com.treehouse.model.AdminLogin;
import com.treehouse.model.DTO.AdminDTO;
import com.treehouse.service.AdminService;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private AdminLoginRepo adminlogr;

	@Override
	public Admin registerAdmin(Admin admin) throws AdminException {

		if (admin == null) {
			throw new AdminException("No Admin Found");
		}
		return adminRepo.save(admin);
	}

	@Override
	public AdminLogin loginAdmin(AdminDTO adminDto) throws AdminException {

		Admin admin = adminRepo.findByAdminEmailAndAdminPassword(adminDto.getUsername(), adminDto.getPassword());

		if (admin != null) {

			Optional<AdminLogin> adminLog = adminlogr.findById(admin.getAdminId());
			if (adminLog.isEmpty()) {
//
				AdminLogin adlogin = new AdminLogin();
				adlogin.setAdminId(admin.getAdminId());

				String key = RandomString.make(10);
				adlogin.setKey(key);

				adlogin.setTimestamp(LocalDateTime.now());

				return adminlogr.save(adlogin);

//				System.out.println(admin);
			}

			throw new AdminException("Admin already Logged in");

		}
		throw new AdminException("Please provide valid inputs");

	}

	@Override
	public Admin updateAdmin(Admin admin) throws AdminException {

		Optional<Admin> oa = adminRepo.findById(admin.getAdminId());

		Admin a = oa.get();

		if (oa.isPresent()) {
			if (admin.getAdminId() == a.getAdminId()) {
				adminRepo.save(admin);
			} else {
				throw new AdminException("Please enter vaild User ID");
			}

		} else {
			throw new AdminException("Admin not Present");
		}

		return a;
	}

	@Override
	public Admin deleteAdmin(String key) throws AdminException {
		
             AdminLogin al = adminlogr.findByKey(key);
		
		if(al!=null) {
		
			Optional<Admin> oa = adminRepo.findById(al.getAdminId());
			
			adminRepo.delete(oa.get());
			
			adminlogr.delete(al);
			
			return oa.get();
		}
			
		else {
			throw new AdminException("Admin not Present");
		}
		
		
		
	}

}
