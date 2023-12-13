package com.aftas_backend.factory.seeders;

import com.aftas_backend.factory.fakers.CompetitionFaker;
import com.aftas_backend.models.entities.Competition;
import com.aftas_backend.repositories.CompetitionRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompetitionSeeder {
    private final CompetitionRepository competitionRepository;
    private final CompetitionFaker competitionFaker;
    public CompetitionSeeder(CompetitionRepository competitionRepository, CompetitionFaker competitionFaker) {
        this.competitionRepository = competitionRepository;
        this.competitionFaker = competitionFaker;
    }
    public void seed(Integer count) {
        List<Competition> competitions = new ArrayList<>();
        for (Integer i = 0; i < count; i++) {
            competitions.add(competitionFaker.makeCompetition());
        }
        competitionRepository.saveAll(competitions);
    }
}
