package com.aftas_backend.services.impl;

import com.aftas_backend.handlers.exceptionHandler.ResourceNotFoundException;
import com.aftas_backend.models.entities.Level;
import com.aftas_backend.repositories.LevelRepository;

import com.aftas_backend.services.LevelService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;
    public LevelServiceImpl(LevelRepository levelRepository){
        this.levelRepository = levelRepository;
    }
    @Override
    public Level createLevel(Level level) {
        level = levelRepository.save(level);
        return setCodeForAllLevels(level);
    }
    @Override
    public Level updateLevel(Level level, Integer code) {
        Level levelToUpdate = getLevelByCode(code);
        levelRepository.save(level);
        return setCodeForAllLevels(level);
    }
    @Override
    public Level getLevelByCode(Integer code) {
        return levelRepository.findByCode(code).orElseThrow(() -> new ResourceNotFoundException("Level not found"));
    }
    @Override
    public void deleteLevelById(Long id) {
        if(existsById(id)){
            levelRepository.deleteById(id);
            setCodeForAllLevels(null);
        }else {
            throw new ResourceNotFoundException("Level does not exist");
        }
    }
    @Override
    public Boolean existsById(Long id) {
        return levelRepository.existsById(id);
    }
    @Override
    public Level findLevelById(Long id) {
        return levelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Level not found"));
    }
    @Override
    public List<Level>  getAllLevels(Pageable pageable) {
        return levelRepository.findAll(pageable).getContent();
    }
    public Level setCodeForAllLevels(Level level){
        List<Level> levels = levelRepository.findAllByOrderByPointsAsc();

        for(Integer i=0;i<levels.size();i++){
            levels.get(i).setCode(i+1);

        }
        levelRepository.saveAll(levels);
        if(level==null){
            return null;
        }
        return findLevelById(level.getId());
    }

}
