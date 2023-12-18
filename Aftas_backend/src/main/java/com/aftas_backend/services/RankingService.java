package com.aftas_backend.services;

import com.aftas_backend.models.entities.Ranking;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RankingService {
    Ranking createRanking(Ranking ranking);
    void deleteRankingByMemberNumberAndCompetitionCode(Integer number, String competitionCode);
    Boolean existsByMemberNumberAndCompetitionCode(Integer number, String competitionCode);
    Ranking updateRanking(Ranking ranking);
    List<Ranking> getAllRankings(Pageable pageable, String search);
    Ranking findRankingByMemberNumberAndCompetitionCode(Integer number, String competitionCode);
    List<Ranking> getAllRankingsByMemberNumber(Pageable pageable, Integer number);
    List<Ranking> getAllRankingsByCompetitionCode(Pageable pageable, String competitionCode);
    List<Ranking> updateAllRankings(Ranking ranking);
}
