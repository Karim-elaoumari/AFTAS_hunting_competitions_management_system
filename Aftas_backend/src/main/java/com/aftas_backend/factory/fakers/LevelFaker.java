package com.aftas_backend.factory.fakers;

import com.aftas_backend.models.entities.Level;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class LevelFaker {
    private Faker faker;
    public LevelFaker() {
        this.faker = new Faker();
    }
    public Level makeLevel(Integer code, Double points){
        return Level.builder()
                .code(code)
                .points(points)
                .description(faker.lorem().sentence())
                .build();
    }
}
