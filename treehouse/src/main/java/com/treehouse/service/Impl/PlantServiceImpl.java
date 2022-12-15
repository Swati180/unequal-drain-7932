package com.treehouse.service.Impl;

import com.treehouse.Repo.PlantRepo;
import com.treehouse.exception.PlantException;
import com.treehouse.model.Plant;
import com.treehouse.service.PlantService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    private PlantRepo pr;
    
    
    @Override
    public Plant addPlant(Plant plant) throws PlantException {
          return pr.save(plant);
    }


	@Override
	public Plant updatePlant(Plant plant) throws PlantException {
		Optional<Plant> s=pr.findById(plant.getPlantId());
		if(s.isPresent()) {
			return pr.save(plant);
		}
		else throw new PlantException("Plant not found with PlantId :"+plant.getPlantId());
	
	}


	@Override
	public Plant deletePlant(Integer plantId) throws PlantException {
		Optional<Plant> s=pr.findById(plantId);
		if(s.isPresent()) {
			Plant pt=s.get();
			pr.delete(pt);
			return pt;
		}
		else throw new PlantException("Plant not found with plantId :"+plantId);
	
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
		if(plants.size()>0)return plants;
        else throw new PlantException("Plants not founds");
	
	}

	@Override
	public List<Plant> viewAllPlant(String typeOfPlant) throws PlantException {
		
		List<Plant> plants=pr.findByTypeOfPlant(typeOfPlant);
		if(plants.size()>0)return plants;
		else throw new PlantException("Plant not found with :"+typeOfPlant);
	
	}
}
