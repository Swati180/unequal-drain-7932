package com.treehouse.service.Impl;

import com.treehouse.Repo.AdminLoginRepo;
import com.treehouse.Repo.PlanterRepo;
import com.treehouse.exception.PlanterException;
import com.treehouse.model.AdminLogin;
import com.treehouse.model.Planter;
import com.treehouse.service.PlanterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanterServiceImpl implements PlanterService {

    @Autowired
    private PlanterRepo planterRepo;
    @Autowired
    private AdminLoginRepo adminLoginRepo;

    @Override
    public Planter addPlanter(Planter planter, String key) throws PlanterException {
        AdminLogin adminLogin=this.adminLogin(key);
        if(adminLogin!=null){
            return planterRepo.save(planter);
        }
        throw new PlanterException("You are not Authorized ");
    }

    @Override
    public Planter updatePlanter(Planter planter, String key) throws PlanterException {
        AdminLogin adminLogin=this.adminLogin(key);
        if(adminLogin!=null){
            Optional<Planter> s = planterRepo.findById(planter.getPlanterId());
            if(s.isPresent()) {
                return planterRepo.save(planter);
            }
            else throw new PlanterException("Planter not found with PlantId :"+planter.getPlanterId());
        }
        throw new  PlanterException("You are not Authorized ");
    }

    @Override
    public Planter deletePlanter(Integer planterId, String key) throws PlanterException {
        AdminLogin adminLogin=this.adminLogin(key);
        if(adminLogin!=null){
            Optional<Planter> s=planterRepo.findById(planterId);
            if(s.isPresent()) {
                Planter pt=s.get();
                planterRepo.delete(pt);
                return pt;
            }
            else throw new PlanterException("Planter not found with plantId :"+planterId);
        }
        throw new  PlanterException("You are not Authorized ");

    }

    @Override
    public Planter viewPlanter(Integer planterId) throws PlanterException {
        return planterRepo.findById(planterId).orElseThrow(()->new PlanterException("Plant not found with plantId :"+planterId));
    }


    @Override
    public List<Planter> viewAllPlanters() throws PlanterException {
        List<Planter> plants=planterRepo.findAll();
        if(plants.size()>0)return plants;
        else throw new PlanterException("Plants not founds");
    }



    // checkPermission of the Admin
    public AdminLogin adminLogin(String key){
        return adminLoginRepo.findByKey(key);
    }
}
