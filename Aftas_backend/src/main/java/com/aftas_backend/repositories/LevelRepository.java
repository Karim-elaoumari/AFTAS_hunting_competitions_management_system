package com.aftas_backend.repositories;

import com.aftas_backend.models.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

    Optional<Level> findByCode(Integer code);
    Boolean existsByCode(Integer code);
    void deleteByCode(Integer code);
    List<Level> findAllByOrderByPointsAsc();

}
