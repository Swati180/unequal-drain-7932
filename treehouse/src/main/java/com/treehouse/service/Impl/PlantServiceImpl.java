package com.treehouse.service.Impl;

import com.treehouse.Repo.AdminLoginRepo;
import com.treehouse.Repo.PlantRepo;
import com.treehouse.exception.PlantException;
import com.treehouse.model.AdminLogin;
import com.treehouse.model.Plant;
import com.treehouse.service.PlantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    private PlantRepo pr;
    @Autowired
	private AdminLoginRepo adminLoginRepo;
    
    
    @Override
    public Plant addPlant(Plant plant,String key) throws PlantException {
    	AdminLogin adminLogin=this.adminLogin(key);
    	if(adminLogin!=null){
			return pr.save(plant);
    	}
    	throw new  PlantException("You are not Authorized ");

    }


	@Override
	public Plant updatePlant(Plant plant,String key) throws PlantException {
		AdminLogin adminLogin=this.adminLogin(key);
		if(adminLogin!=null){
			Optional<Plant> s=pr.findById(plant.getPlantId());
			if(s.isPresent()) {
				return pr.save(plant);
			}
			else throw new PlantException("Plant not found with PlantId :"+plant.getPlantId());
		}
		throw new  PlantException("You are not Authorized ");
	
	}


	@Override
	public Plant deletePlant(Integer plantId,String key) throws PlantException {
		AdminLogin adminLogin=this.adminLogin(key);
		if(adminLogin!=null){
			Optional<Plant> s=pr.findById(plantId);
			if(s.isPresent()) {
				Plant pt=s.get();
				pr.delete(pt);
				return pt;
			}
			else throw new PlantException("Plant not found with plantId :"+plantId);
		}
		throw new  PlantException("You are not Authorized ");

	
	}


	@Override
	public Plant viewPlant(Integer plantId) throws PlantException {
		
		return pr.findById(plantId).orElseThrow(()->new PlantException("Plant not found with plantId :"+plantId));
	}


	@Override
	public List<Plant> viewPlant(String commonName) throws PlantException {

		List<Plant> plants=pr.findByCommonName(commonName);
		if(plants.size()>0)return plants;
		else throw new PlantException("Plant not found with:"+commonName);
		
	}


	@Override
	public List<Plant> viewAllPlants() throws PlantException {

		List<Plant> plants=pr.findAll();
		List<Plant> list=new ArrayList<>();
		for(Plant i:plants){
			String download= ServletUriComponentsBuilder.fromCurrentContextPath().path("/target/static/images/").path(i.getPlantImage()).toUriString();
			i.setPlantImage(download);
			list.add(i);
		}
		return  list;
	}

	@Override
	public List<Plant> viewAllPlant(String typeOfPlant) throws PlantException {
		
		List<Plant> plants=pr.findByTypeOfPlant(typeOfPlant);
		if(plants.size()>0)return plants;
		else throw new PlantException("Plant not found with :"+typeOfPlant);
	
	}
	// checkPermission of the ADmin
	public AdminLogin adminLogin(String key){
    	return adminLoginRepo.findByKey(key);
	}
}
