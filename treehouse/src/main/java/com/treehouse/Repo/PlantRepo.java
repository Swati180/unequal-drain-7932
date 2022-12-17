package com.treehouse.Repo;

import com.treehouse.model.Plant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepo extends JpaRepository<Plant,Integer> {
	
	
    public List<Plant> findByCommonName(String commonName);
	
	public List<Plant> findByTypeOfPlant(String typeOfPlant);
}
