package com.aftas_backend.factory.fakers;

import com.aftas_backend.models.entities.Fish;
import com.aftas_backend.models.entities.Level;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;


import java.util.List;
@Component
public class FishFaker {
    private Faker faker;
    public FishFaker() {
        this.faker = new Faker();
    }
    public Fish makeFish(Level level,String name){
        return Fish.builder()
                .name(name)
                .level(level)
                .averageWeight(faker.number().randomDouble(2, 0, 100))
                .info(faker.lorem().sentence())
                .build();
    }
}
