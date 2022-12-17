package com.treehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treehouse.exception.PlantException;
import com.treehouse.model.Plant;
import com.treehouse.service.PlantService;

@RestController
@RequestMapping("/admin/plant")
public class PlantController {

	@Autowired
	private PlantService ps;

	@PostMapping("/addPlant/{admin_key}")
	public ResponseEntity<Plant> addPlant(@RequestBody Plant plant,@PathVariable("admin_key") String key)throws PlantException{
		Plant plant2=ps.addPlant(plant,key);
		return new ResponseEntity<Plant>(plant2,HttpStatus.CREATED);
	}
	
	@PutMapping("/updatePlant/{admin_key}")
	public ResponseEntity<Plant> updateSeed(@RequestBody Plant plant,@PathVariable("admin_key") String key)throws PlantException{
		Plant plant2=ps.updatePlant(plant,key);
		return new ResponseEntity<Plant>(plant2,HttpStatus.OK);
	}
	
	@DeleteMapping("/deletePlant/{pid}/{admin_key}")
	public ResponseEntity<Plant> deletePlant(@PathVariable("pid") Integer pid,@PathVariable("admin_key") String key)throws PlantException{
		Plant plant2=ps.deletePlant(pid,key);
		return new ResponseEntity<Plant>(plant2,HttpStatus.OK);
	}
	
	@GetMapping("/viewPlantByPid/{pid}")
	public ResponseEntity<Plant> viewPlant(@PathVariable("pid") Integer pid)throws PlantException{
		Plant plant2=ps.viewPlant(pid);
		return new ResponseEntity<Plant>(plant2,HttpStatus.OK);
	}
	
	@GetMapping("/viewPlantByCommonName/{cn}")
	public ResponseEntity<List<Plant>>viewPlant(@PathVariable("cn") String cname)throws PlantException{
		List<Plant> plants=ps.viewPlant(cname);
		return new ResponseEntity<List<Plant>>(plants,HttpStatus.OK);
	}
	
	@GetMapping("/AllPlants")
	public ResponseEntity<List<Plant>>viewAllSeeds()throws PlantException{
		List<Plant> plants=ps.viewAllPlants();
		return new ResponseEntity<List<Plant>>(plants,HttpStatus.OK);
	}
	
	@GetMapping("/viewPlantByTop/{toP}")
	public ResponseEntity<List<Plant>> viewPlantsByType(@PathVariable("toP") String TPlants)throws PlantException{
		List<Plant> plants=ps.viewAllPlant(TPlants);
		return new ResponseEntity<List<Plant>>(plants,HttpStatus.OK);
	}
	
	
	
}
