package com.treehouse.service;

import com.treehouse.exception.CustomerExecption;
import com.treehouse.exception.PlantException;
import com.treehouse.model.Customer;
import com.treehouse.model.DTO.CustomerDTO;
import com.treehouse.model.Plant;

public interface PlantService {

    public Plant registerCustomer(Plant plant) throws PlantException;

}
