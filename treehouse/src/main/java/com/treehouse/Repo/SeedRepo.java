package com.treehouse.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.treehouse.model.Seeds;

@Repository
public interface SeedRepo extends JpaRepository<Seeds,Integer>{

	public List<Seeds> findByCommonName(String commonName);
	
	public List<Seeds> findByTypeOfSeeds(String typeOfSeeds);
}
