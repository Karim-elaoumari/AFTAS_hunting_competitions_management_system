package com.aftas_backend.services;

import com.aftas_backend.models.entities.Competition;
import com.aftas_backend.models.entities.Member;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface CompetitionService {
    List<Competition> getAllCompetitions(Pageable pageable, String search, LocalDate date);
    Competition getCompetitionById(String id);
    Competition createCompetition(Competition competition);
    Competition updateCompetition(Competition competition,String id);
    void deleteCompetition(String id);
    Boolean existsById(String id);
    Long countCompetitions();

}
