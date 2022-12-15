package com.treehouse.service.Impl;

import com.treehouse.Repo.PlantRepo;
import com.treehouse.exception.PlantException;
import com.treehouse.model.Plant;
import com.treehouse.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    private PlantRepo plantRepo;
    @Override
    public Plant registerCustomer(Plant plant) throws PlantException {


        return plantRepo.save(plant);
    }
}
