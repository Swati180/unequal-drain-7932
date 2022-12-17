package com.treehouse.service.Impl;

import java.util.List;
import java.util.Optional;

import com.treehouse.Repo.AdminLoginRepo;
import com.treehouse.model.AdminLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treehouse.Repo.SeedRepo;
import com.treehouse.exception.SeedException;
import com.treehouse.model.Seeds;
import com.treehouse.service.SeedService;

@Service
public class SeedServiceImp implements SeedService{

	@Autowired
	private SeedRepo sr;
	@Autowired
	private AdminLoginRepo adminLoginRepo;

	@Override
	public Seeds addSeeds(Seeds seeds,String key) throws SeedException {
		AdminLogin adminLogin=this.adminLogin(key);
		if(adminLogin!=null){
			return sr.save(seeds);
		}
		throw new SeedException("You are Not Authorized");
	}

	@Override
	public Seeds updateSeeds(Seeds seeds,String key) throws SeedException {
		AdminLogin adminLogin=this.adminLogin(key);
		if(adminLogin!=null) {
			Optional<Seeds> s=sr.findById(seeds.getSeedsId());
			if(s.isPresent()) {
				return sr.save(seeds);
			}
			else throw new SeedException("Seed not found with seedId :"+seeds.getSeedsId());
		}
		throw new SeedException("You are Not Authorized");
	}

	@Override
	public Seeds deleteSeeds(Integer seedsId,String key) throws SeedException {
		AdminLogin adminLogin=this.adminLogin(key);
		if(adminLogin!=null) {
			Optional<Seeds> s=sr.findById(seedsId);
			if(s.isPresent()) {
				Seeds seeds=s.get();
				sr.delete(seeds);
				return seeds;
			}
			else throw new SeedException("Seed not found with seedId :"+seedsId);
		}
		throw new SeedException("You are Not Authorized");
	}

	@Override
	public Seeds viewSeeds(Integer seedsId) throws SeedException {
		
		 Optional<Seeds>seeds=sr.findById(seedsId);
		 if(seeds.isPresent()){
		 	System.out.print(seeds.get());
		 	return seeds.get();
		 }
		throw  new SeedException("Seed not found with seedId :"+seedsId);
	}

	@Override
	public List<Seeds> viewSeeds(String commonName) throws SeedException {
		
		List<Seeds> seeds=sr.findByCommonName(commonName);
		if(seeds.size()>0)return seeds;
		else throw new SeedException("Seed not found with:"+commonName);
		
	}

	@Override
	public List<Seeds> viewAllSeeds() throws SeedException {
		
		List<Seeds> seeds=sr.findAll();
		if(seeds.size()>0)return seeds;
        else throw new SeedException("Seeds not founds");
	}

	@Override
	public List<Seeds> viewAllSeeds(String typeOfSeed) throws SeedException {
		
		List<Seeds> seeds=sr.findByTypeOfSeeds(typeOfSeed);
		if(seeds.size()>0)return seeds;
		else throw new SeedException("Seed not found with :"+typeOfSeed);
	}
	public AdminLogin adminLogin(String key){
		return adminLoginRepo.findByKey(key);
	}

}
