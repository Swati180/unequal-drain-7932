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

import com.treehouse.exception.SeedException;
import com.treehouse.model.Seeds;
import com.treehouse.service.SeedService;

@RestController
@RequestMapping("/admin/seeds")
public class SeedController {

	@Autowired
	private SeedService ss;
	
	@PostMapping("/addSeed/{admin_key}")
	public ResponseEntity<Seeds> addSeed(@RequestBody Seeds seeds,@PathVariable("admin_key") String key)throws SeedException{
		Seeds seeds2=ss.addSeeds(seeds,key);
		return new ResponseEntity<Seeds>(seeds2,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateSeed/{admin_key}")
	public ResponseEntity<Seeds> updateSeed(@RequestBody Seeds seeds,@PathVariable("admin_key") String key)throws SeedException{
		Seeds seeds2=ss.updateSeeds(seeds,key);
		return new ResponseEntity<Seeds>(seeds2,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteSeed/{sid}/{admin_key}")
	public ResponseEntity<Seeds> deleteSeed(@PathVariable("sid") Integer sid,@PathVariable("admin_key") String key)throws SeedException{
		Seeds seeds2=ss.deleteSeeds(sid,key);
		return new ResponseEntity<Seeds>(seeds2,HttpStatus.OK);
	}
	
	@GetMapping("/viewSeedBySid/{sid}")
	public ResponseEntity<Seeds> viewSeed(@PathVariable("sid") Integer sid)throws SeedException{
		Seeds seeds2=ss.viewSeeds(sid);
		return new ResponseEntity<Seeds>(seeds2,HttpStatus.OK);
	}
	
	@GetMapping("/viewSeedCommonName/{cn}")
	public ResponseEntity<List<Seeds>>viewSeeds(@PathVariable("cn") String cname)throws SeedException{
		List<Seeds> seeds2=ss.viewSeeds(cname);
		return new ResponseEntity<List<Seeds>>(seeds2,HttpStatus.OK);
	}
	
	@GetMapping("/AllSeed")
	public ResponseEntity<List<Seeds>>viewAllSeeds()throws SeedException{
		List<Seeds> seeds2=ss.viewAllSeeds();
		return new ResponseEntity<List<Seeds>>(seeds2,HttpStatus.OK);
	}
	
	@GetMapping("/viewSeedTypeOfTos/{tos}")
	public ResponseEntity<List<Seeds>>viewSeedsByType(@PathVariable("tos") String TSeeds)throws SeedException{
		List<Seeds> seeds2=ss.viewAllSeeds(TSeeds);
		return new ResponseEntity<List<Seeds>>(seeds2,HttpStatus.OK);
	}

}
