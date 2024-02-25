package com.aftas_backend.factory.seeders;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class
MainSeeder implements CommandLineRunner {
    private final CompetitionSeeder competitionSeeder;
    private final FishSeeder fishSeeder;
    private final MemberSeeder memberSeeder;
    private final LevelSeeder levelSeeder;
    private final RankingSeeder rankingSeeder;



    @Override
    public void run(String... args) throws Exception {

        memberSeeder.seed(5);
        competitionSeeder.seed(10);
        levelSeeder.seed(4);
        fishSeeder.seed(2);
        rankingSeeder.seed();
    }
}
