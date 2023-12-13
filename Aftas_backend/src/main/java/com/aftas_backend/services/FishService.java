package com.aftas_backend.services;

import com.aftas_backend.models.entities.Fish;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FishService {
    List<Fish> getAllFishes(Pageable pageable, String search);
    Fish createFish(Fish fish);
    Fish updateFish(Fish fish, Long id);
    Fish getFishById(Long id);
    Fish getFishByName(String name);
    void deleteFish(Long id);
    Boolean existsByName(String name);
    Boolean existsById(Long id);
}
