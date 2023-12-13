package com.aftas_backend.web.rest.vms.competition;

import com.aftas_backend.models.entities.Competition;
import com.aftas_backend.web.rest.vms.ranking.RankingResponseComVM;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record CompetitionResponseRankVM(
        String code,
        String location,
        LocalDate date,
        LocalTime start_time,
        LocalTime end_time,
        Double amount,
        Integer max_participants,
        String description
) {
    public static CompetitionResponseRankVM fromCompetition(Competition competition) {
        return new CompetitionResponseRankVM(
                competition.getCode(),
                competition.getLocation(),
                competition.getDate(),
                competition.getStartTime(),
                competition.getEndTime(),
                competition.getAmount(),
                competition.getMaxParticipants(),
                competition.getDescription()

        );
    }

}