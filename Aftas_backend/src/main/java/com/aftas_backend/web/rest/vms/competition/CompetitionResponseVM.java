package com.aftas_backend.web.rest.vms.competition;


import com.aftas_backend.models.entities.Competition;
import com.aftas_backend.web.rest.vms.ranking.RankingResponseComVM;
import com.aftas_backend.web.rest.vms.ranking.RankingResponseVM;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public record CompetitionResponseVM(
        String code,
        String location,
        LocalDate date,
        LocalTime start_time,
        LocalTime end_time,
        Double amount,
        Integer max_participants,
        String description,
        List<RankingResponseComVM> rankings
) {
    public static CompetitionResponseVM fromCompetition(Competition competition) {

        return new CompetitionResponseVM(
                competition.getCode(),
                competition.getLocation(),
                competition.getDate(),
                competition.getStartTime(),
                competition.getEndTime(),
                competition.getAmount(),
                competition.getMaxParticipants(),
                competition.getDescription(),
                competition.getRankings() == null ? new ArrayList<>() : competition.getRankings().stream()
                        .map(RankingResponseComVM::fromRanking)
                        .toList()
        );
    }

}
