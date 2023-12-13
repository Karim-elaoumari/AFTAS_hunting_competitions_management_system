package com.aftas_backend.factory.fakers;

import com.aftas_backend.helpers.CompetitionCodeGen;
import com.aftas_backend.models.entities.Competition;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Component
public class CompetitionFaker {
    private Faker faker ;
    private final CompetitionCodeGen competitionCodeGen;
    public CompetitionFaker(CompetitionCodeGen competitionCodeGen) {
        this.competitionCodeGen = competitionCodeGen;
        this.faker = new Faker();
    }
    public Competition makeCompetition(){
        LocalDate date = LocalDate.now().plusDays(faker.number().randomDigit());
         LocalTime startTime = date.atStartOfDay().toLocalTime();
         LocalTime endTime = startTime.plusHours(4);
         String location = faker.address().city();
        return Competition.builder()
                .code(competitionCodeGen.generateCode(location,date))
                .amount(faker.number().randomDouble(2, 100, 1000))
                .description(faker.lorem().sentence())
                .maxParticipants(faker.number().randomDigit())
                .location(location)
                .startTime(startTime)
                .endTime(endTime)
                .date(date)
                .build();
    }

}
