package com.treehouse.controller;

import com.treehouse.exception.PlanterException;
import com.treehouse.model.Planter;
import com.treehouse.service.PlanterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/admin/planter")
public class PlanterController {


        @Autowired
        private PlanterService planterService;

        @PostMapping("/addPlanter/{admin_key}")
        public ResponseEntity<Planter> addPlant(@RequestBody Planter plant, @PathVariable("admin_key") String key)throws PlanterException {
            Planter planters=planterService.addPlanter(plant,key);
            return new ResponseEntity<Planter>(planters, HttpStatus.CREATED);
        }

        @PutMapping("/updatePlanter/{admin_key}")
        public ResponseEntity<Planter> updateSeed(@RequestBody Planter plant,@PathVariable("admin_key") String key)throws PlanterException{
            Planter planters=planterService.updatePlanter(plant,key);
            return new ResponseEntity<Planter>(planters,HttpStatus.OK);
        }

        @DeleteMapping("/deletePlanter/{pid}/{admin_key}")
        public ResponseEntity<Planter> deletePlanter(@PathVariable("pid") Integer pid,@PathVariable("admin_key") String key)throws PlanterException{
            Planter planters=planterService.deletePlanter(pid,key);
            return new ResponseEntity<Planter>(planters,HttpStatus.OK);
        }

        @GetMapping("/viewPlanterByPid/{pid}")
        public ResponseEntity<Planter> viewPlant(@PathVariable("pid") Integer pid)throws PlanterException{
            Planter planters=planterService.viewPlanter(pid);
            return new ResponseEntity<Planter>(planters,HttpStatus.OK);
        }


        @GetMapping("/AllPlanters")
        public ResponseEntity<List<Planter>>viewAllSeeds()throws PlanterException{
            List<Planter> planters=planterService.viewAllPlanters();
            return new ResponseEntity<List<Planter>>(planters,HttpStatus.OK);
        }



    }
