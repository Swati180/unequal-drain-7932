package com.treehouse.Repo;

import com.treehouse.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
	// ADMIN
	// /admin/register
	// /admin/login
	// /admin/update
	// /admin/delete
	// /admin/All
	// SEED
	// /admin/seeds/create/{key}
	// /admin/seed/update/{key}
	// /admin/seed/delete/{key}
	// /admin/seed/getById/{key}
	// PLANT
	// /admin/plant/create/{key}
	// /admin/plant/update/{key}
	// /admin/plant/delete/{key}
	// /admin/plant/getById/{key}
	// PLANTER
	// /admin/planter/create/{key}
	// /admin/planter/update/{key}
	// /admin/planter/delete/{key}
	// /admin/planter/getById/{key}
	// CUSTOMER
	// /admin/customer/all

	// CUSTOMERAPI
	// /customer/create
	// /customer/update/{key}
	// /customer/delete/{key}
	// /customer/getById/
	// /customer/login
	// /customer/AllPlanters
	// /customer/AllPlant
	// /customer/AllSeeds
	// /customer/BuyPlanters/{Planter_Id}/{key}
	// /customer/BuyPlant/{Plant_Id}/{key}
	// /customer/BuySeeds/{Seeds_Id}/{key}

//    Order
//    /order/create
//    / order/delete
//    /order/update
//    /order/viewall
//    /order/orderbyid/{id}

	public Admin findByAdminEmailAndAdminPassword(String adminEmail, String adminPassword);

}
