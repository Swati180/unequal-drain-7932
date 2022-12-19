package com.treehouse.Repo;

import com.treehouse.model.DTO.PlanterDto;
import com.treehouse.model.Planter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanterDtoRepo extends JpaRepository<PlanterDto,Integer> {
}
