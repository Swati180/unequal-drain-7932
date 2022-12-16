package com.treehouse.service;

import java.util.List;

import com.treehouse.exception.PlantException;
import com.treehouse.model.Plant;

public interface PlantService {

    public Plant addPlant(Plant plant,String key) throws PlantException;
    
    public Plant updatePlant(Plant plant,String key)throws PlantException;
    
    public Plant deletePlant(Integer plantId,String key)throws PlantException;
    
    public Plant viewPlant(Integer plantId)throws PlantException;
    
    public List<Plant> viewPlant(String commonName)throws PlantException;
    
    public List<Plant> viewAllPlants()throws PlantException;
    
    public List<Plant> viewAllPlant(String typeOfPlant)throws PlantException;
    
    
    

}
