package com.aftas_backend.services.impl;

import com.aftas_backend.handlers.exceptionHandler.ResourceNotFoundException;
import com.aftas_backend.models.entities.Fish;
import com.aftas_backend.models.entities.Hunting;
import com.aftas_backend.models.entities.Level;
import com.aftas_backend.repositories.FishRepository;
import com.aftas_backend.services.FishService;
import com.aftas_backend.services.LevelService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishServiceImpl implements FishService {
    private final FishRepository fishRepository;
    private final LevelService levelService;
    public FishServiceImpl(FishRepository fishRepository, LevelService levelService){
        this.fishRepository = fishRepository;
        this.levelService = levelService;
    }
    @Override
    public Boolean existsByName(String name){
        return fishRepository.existsByName(name);
    }
    @Override
    public Boolean existsById(Long id){
        return fishRepository.existsById(id);
    }
    @Override
    public void deleteFish(Long id){
        Fish fish = getFishById(id);
        fishRepository.delete(fish);
    }
    @Override
    public Fish getFishByName(String name){
        return fishRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Fish not found"));
    }
    @Override
    public Fish getFishById(Long id){
        return fishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fish not found"));
    }
    @Override
    public Fish createFish(Fish fish){
        Level level = levelService.getLevelByCode(fish.getLevel().getCode());
        fish.setLevel(level);
        if(existsByName(fish.getName())){
            throw new ResourceNotFoundException("Fish already exists");
        }
        return fishRepository.save(fish);
    }
    @Override
    public Fish updateFish(Fish fish, Long id){
        Fish fish1 = getFishById(id);
        if(fish.getLevel().getCode()==fish1.getLevel().getCode()){
            fish.setLevel(fish1.getLevel());
        }else {
            Level level = levelService.getLevelByCode(fish.getLevel().getCode());
            fish.setLevel(level);
        }
        fish.setId(id);
        return fishRepository.save(fish);
    }
    @Override
    public List<Fish> getAllFishes(Pageable pageable,String search){
        if(search==null){
            return fishRepository.findAll(pageable).getContent();
        }
        return fishRepository.findByNameContainingOrLevelCodeContainingOrWeightContaining(search,search,search,pageable).getContent();
    }
    @Override
    public Long countFishes(){
        return fishRepository.count();
    }

}
