package com.treehouse.Repo;

import com.treehouse.model.DTO.PlantDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantDtoRepo extends JpaRepository<PlantDto,Integer> {
}
