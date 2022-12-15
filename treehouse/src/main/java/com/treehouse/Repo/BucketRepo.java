package com.treehouse.Repo;

import com.treehouse.model.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepo extends JpaRepository<Bucket,Integer> {
}
