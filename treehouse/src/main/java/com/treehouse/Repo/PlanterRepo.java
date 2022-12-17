package com.treehouse.Repo;

import com.treehouse.model.Plant;
import com.treehouse.model.Planter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PlanterRepo extends JpaRepository<Planter,Integer> {

}
