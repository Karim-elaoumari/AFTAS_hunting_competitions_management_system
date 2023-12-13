package com.aftas_backend.services;

import com.aftas_backend.models.entities.Level;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LevelService {
    Level createLevel(Level level);
    Level updateLevel(Level level, Integer code);
    Level getLevelByCode(Integer code);
    void deleteLevelById(Long id);
    Level findLevelById(Long id);
    List<Level> getAllLevels(Pageable pageable);
    Boolean existsById(Long id);
}
