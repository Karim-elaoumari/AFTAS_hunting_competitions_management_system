package com.aftas_backend.repositories;

import com.aftas_backend.models.entities.Fish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long>{
    Boolean existsByName(String name);

    @Override
    Optional<Fish> findById(Long aLong);
    Optional<Fish> findByName(String name);
//    find by name containing or level code containing or fish weight containing
    @Query("SELECT f FROM Fish f WHERE f.name LIKE %?1% OR cast(f.level.code as STRING)  LIKE %?2% OR cast(f.averageWeight as STRING )  LIKE %?3%")
    Page<Fish> findByNameContainingOrLevelCodeContainingOrWeightContaining(String name, String levelCode, String fishWeight, Pageable pageable);
}
