package com.treehouse.service;

import java.util.List;

import com.treehouse.exception.SeedException;
import com.treehouse.model.Seeds;

public interface SeedService {
	
    public Seeds addSeeds(Seeds seeds,String key)throws SeedException;
    public Seeds updateSeeds(Seeds seeds,String key)throws SeedException;
    public Seeds deleteSeeds(Integer seedsId,String key)throws SeedException;
    public Seeds viewSeeds(Integer seedsId)throws SeedException;
    public List<Seeds> viewSeeds(String commonName)throws SeedException;
    public List<Seeds> viewAllSeeds()throws SeedException;
    public List<Seeds> viewAllSeeds(String typeOfSeed)throws SeedException;

    
}
