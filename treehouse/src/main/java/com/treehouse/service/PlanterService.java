package com.treehouse.service;

import com.treehouse.exception.PlantException;
import com.treehouse.exception.PlanterException;
import com.treehouse.model.Plant;
import com.treehouse.model.Planter;

import java.util.List;

public interface PlanterService {

    public Planter addPlanter(Planter planter, String key) throws PlanterException;
    public Planter updatePlanter(Planter planter,String key)throws PlanterException;
    public Planter deletePlanter(Integer planterId,String key)throws PlanterException;
    public Planter viewPlanter(Integer planterId)throws PlanterException;
    public List<Planter> viewAllPlanters()throws PlanterException;

}
