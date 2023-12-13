package com.aftas_backend.factory.seeders;

import com.aftas_backend.factory.fakers.FishFaker;
import com.aftas_backend.models.entities.Fish;
import com.aftas_backend.models.entities.Level;
import com.aftas_backend.repositories.FishRepository;
import com.aftas_backend.repositories.LevelRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FishSeeder {
    private final LevelRepository levelRepository;
    private final FishRepository fishRepository;
    private final FishFaker fishFaker;
    public FishSeeder(LevelRepository levelRepository, FishRepository fishRepository, FishFaker fishFaker) {
        this.levelRepository = levelRepository;
        this.fishRepository = fishRepository;
        this.fishFaker = fishFaker;
    }
    public void seed(Integer count) {
        List<Level> levels = levelRepository.findAll();
        List<Fish> fishes =  new ArrayList<>();
        List<String> fishNames = new ArrayList<>(Arrays.asList(
                "Salmon", "Trout", "Tuna", "Bass", "Catfish", "Cod", "Haddock",
                "Mahi-mahi", "Grouper", "Snapper", "Perch", "Pike", "Flounder",
                "Mackerel", "Halibut", "Swordfish", "Carp", "Tilapia", "Sardine", "Anchovy"
        ));
        if(count*levels.size() > fishNames.size()){
            throw new RuntimeException("Fish names are not enough in fish seeder");
        }
        else{
            for(int j = 0; j < levels.size(); j++){
                for (int i = 0; i < count; i++) {
                    fishes.add(fishFaker.makeFish(levels.get(j),fishNames.get(j*count+i)));
                }
            }
        }
        fishRepository.saveAll(fishes);

    }

}
