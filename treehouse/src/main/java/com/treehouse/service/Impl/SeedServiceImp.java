package com.treehouse.service.Impl;

import java.util.List;
import java.util.Optional;

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
	
	
	@Override
	public Seeds addSeeds(Seeds seeds) throws SeedException {
		return sr.save(seeds);
	}

	@Override
	public Seeds updateSeeds(Seeds seeds) throws SeedException {
		Optional<Seeds> s=sr.findById(seeds.getSeedsId());
		if(s.isPresent()) {
			return sr.save(seeds);
		}
		else throw new SeedException("Seed not found with seedId :"+seeds.getSeedsId());
	}

	@Override
	public Seeds deleteSeeds(Integer seedsId) throws SeedException {
		Optional<Seeds> s=sr.findById(seedsId);
		if(s.isPresent()) {
			Seeds seeds=s.get();
			sr.delete(seeds);
			return seeds;
		}
		else throw new SeedException("Seed not found with seedId :"+seedsId);
	}

	@Override
	public Seeds viewSeeds(Integer seedsId) throws SeedException {
		
		return sr.findById(seedsId).orElseThrow(()->new SeedException("Seed not found with seedId :"+seedsId));
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

}
