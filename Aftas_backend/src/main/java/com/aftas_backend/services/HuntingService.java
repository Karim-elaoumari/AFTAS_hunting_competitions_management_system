package com.aftas_backend.services;

import com.aftas_backend.models.dto.HuntingDTO;
import com.aftas_backend.models.entities.Hunting;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HuntingService {
    Hunting createHunting(HuntingDTO hunting);
    List<Hunting> getHuntingsByMemberNumber(Integer memberNumber, Pageable pageable);
    Hunting getHuntingById(String id);
    List<Hunting> getHuntingsByMemberNumberAndCompetitionCode(Integer memberNumber, String competitionCode, Pageable pageable);
    List<Hunting> getHuntingsByCompetitionCode(String competitionCode, Pageable pageable);
    void deleteHunting(String id);
    Boolean existsById(String id);
    Boolean existsHuntingByMemberNumberAndFishIdAndCompetitionCode(Integer memberNumber, Long fishId, String competitionCode);
    Hunting findHuntingByMemberNumberAndFishIdAndCompetitionCode(Integer memberNumber, Long fishId, String competitionCode);
}
