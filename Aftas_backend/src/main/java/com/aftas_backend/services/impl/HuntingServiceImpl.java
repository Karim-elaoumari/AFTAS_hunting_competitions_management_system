package com.aftas_backend.services.impl;

import com.aftas_backend.handlers.exceptionHandler.OperationException;
import com.aftas_backend.handlers.exceptionHandler.ResourceNotFoundException;
import com.aftas_backend.models.dto.HuntingDTO;
import com.aftas_backend.models.entities.*;
import com.aftas_backend.repositories.HuntingRepository;
import com.aftas_backend.services.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class HuntingServiceImpl implements HuntingService {
    private final HuntingRepository huntingRepository;
    private final RankingService  rankingService;
    private final FishService fishService;
    public HuntingServiceImpl(HuntingRepository huntingRepository, RankingService rankingService, FishService fishService) {
        this.huntingRepository = huntingRepository;
        this.rankingService = rankingService;
        this.fishService = fishService;
    }
    @Override
    public void deleteHunting(String id) {
        getHuntingById(id);
        huntingRepository.deleteById(id);
    }
    @Override
    public Hunting getHuntingById(String id) {
        return huntingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hunting not found"));
    }
    @Override
    public Hunting createHunting(HuntingDTO huntingDTO) {

        if(rankingService.existsByMemberNumberAndCompetitionCode(huntingDTO.getMember_number(),huntingDTO.getCompetition_code())==false){
            throw new OperationException("This member is not signed this competition");
        }
        Ranking ranking = rankingService.findRankingByMemberNumberAndCompetitionCode(huntingDTO.getMember_number(),huntingDTO.getCompetition_code());
        if(ranking.getCompetition().getDate().isEqual(LocalDate.now())==false){
            throw new OperationException("Competition is not today to start hunting");
        }
        if(ranking.getCompetition().getStartTime().isAfter(LocalTime.now())){
            throw new OperationException("Competition has not started yet still some hours ");
        }
        if(ranking.getCompetition().getEndTime().isBefore(LocalTime.now())){
            throw new OperationException("Competition has been finished");
        }
        Fish fish = fishService.getFishById(huntingDTO.getFish_id());
        if(fish.getAverageWeight()>huntingDTO.getWeight()){
            throw new ResourceNotFoundException("Fish weight is less than average weight");
        }
        if(existsHuntingByMemberNumberAndFishIdAndCompetitionCode(huntingDTO.getMember_number(),huntingDTO.getFish_id(),huntingDTO.getCompetition_code())==true){
            Hunting hunting1 = findHuntingByMemberNumberAndFishIdAndCompetitionCode(huntingDTO.getMember_number(),huntingDTO.getFish_id(),huntingDTO.getCompetition_code());
            hunting1.setNumberOfFish(hunting1.getNumberOfFish()+1);
            ranking.setScore(ranking.getScore() + fish.getLevel().getPoints());
            rankingService.updateRanking(ranking);
            return huntingRepository.save(hunting1);
        }else{
            Hunting newHunting  = Hunting.builder()
                    .member(ranking.getMember())
                    .competition(ranking.getCompetition())
                    .fish(fish)
                    .numberOfFish(1)
                    .build();
            ranking.setScore(ranking.getScore() + fish.getLevel().getPoints());
            rankingService.updateRanking(ranking);
            return huntingRepository.save(newHunting);
        }
    }
    @Override
    public Boolean existsById(String id) {
        return huntingRepository.existsById(id);
    }
    @Override
    public List<Hunting> getHuntingsByMemberNumber(Integer memberNumber, Pageable pageable) {
        return huntingRepository.findAllByMember_Number(memberNumber, pageable).getContent();
    }
    @Override
    public List<Hunting> getHuntingsByMemberNumberAndCompetitionCode(Integer memberNumber, String competitionCode, Pageable pageable) {
        return huntingRepository.findAllByMember_NumberAndCompetition_Code(memberNumber, competitionCode, pageable).getContent();
    }
    @Override
    public List<Hunting> getHuntingsByCompetitionCode(String competitionCode, Pageable pageable) {
        return huntingRepository.findAllByCompetition_Code(competitionCode, pageable).getContent();
    }
    @Override
    public Boolean existsHuntingByMemberNumberAndFishIdAndCompetitionCode(Integer memberNumber, Long fishId, String competitionCode) {
        return huntingRepository.existsByMember_NumberAndFish_IdAndCompetition_Code(memberNumber, fishId,competitionCode);
    }
    @Override
    public Hunting findHuntingByMemberNumberAndFishIdAndCompetitionCode(Integer memberNumber, Long fishId, String competitionCode) {
        return huntingRepository.findByMember_NumberAndFish_IdAndCompetition_Code(memberNumber, fishId,competitionCode).orElseThrow(() -> new ResourceNotFoundException("Hunting not found"));
    }

}
