package com.treehouse.Repo;

import com.treehouse.model.DTO.SeedsDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeedsDtoRepo extends JpaRepository<SeedsDto,Integer> {
}
