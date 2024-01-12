package com.aftas_backend.factory.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainSeeder implements CommandLineRunner {
    private final CompetitionSeeder competitionSeeder;
    private final FishSeeder fishSeeder;
    private final MemberSeeder memberSeeder;
    private final LevelSeeder levelSeeder;

    public MainSeeder(CompetitionSeeder competitionSeeder, FishSeeder fishSeeder, MemberSeeder memberSeeder, LevelSeeder levelSeeder) {
        this.competitionSeeder = competitionSeeder;
        this.fishSeeder = fishSeeder;
        this.memberSeeder = memberSeeder;
        this.levelSeeder = levelSeeder;
    }

    @Override
    public void run(String... args) throws Exception {
        memberSeeder.seed(20);
        competitionSeeder.seed(5);
        levelSeeder.seed(4);
        fishSeeder.seed(2);



    }
}
