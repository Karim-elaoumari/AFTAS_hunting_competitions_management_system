package com.aftas_backend.factory.seeders;

import com.aftas_backend.factory.fakers.LevelFaker;
import com.aftas_backend.models.entities.Level;
import com.aftas_backend.repositories.LevelRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LevelSeeder {
    private final LevelFaker levelFaker;
    private final LevelRepository levelRepository;
    public LevelSeeder(LevelFaker levelFaker, LevelRepository levelRepository) {
        this.levelFaker = levelFaker;
        this.levelRepository = levelRepository;
    }
    public void seed(Integer count) {
        List<Level>  levels = new ArrayList<>();
        for (Integer i = 1; i <= count; i++) {
            levels.add(levelFaker.makeLevel(i, (Double) i.doubleValue()*13));
        }
        levelRepository.saveAll(levels);
    }
}
