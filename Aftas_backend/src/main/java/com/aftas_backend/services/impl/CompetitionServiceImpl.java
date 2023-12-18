package com.aftas_backend.services.impl;

import com.aftas_backend.handlers.exceptionHandler.OperationException;
import com.aftas_backend.handlers.exceptionHandler.ResourceNotFoundException;
import com.aftas_backend.helpers.CompetitionCodeGen;
import com.aftas_backend.models.entities.Competition;
import com.aftas_backend.models.entities.Member;
import com.aftas_backend.models.entities.Ranking;
import com.aftas_backend.repositories.CompetitionRepository;
import com.aftas_backend.services.CompetitionService;
import com.aftas_backend.services.MemberService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Timer;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final CompetitionCodeGen competitionCodeGen;
    private final MemberService memberService;
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, CompetitionCodeGen competitionCodeGen, MemberService memberService) {
        this.competitionRepository = competitionRepository;
        this.competitionCodeGen = competitionCodeGen;
        this.memberService = memberService;
    }
    @Override
    public List<Competition> getAllCompetitions(Pageable pageable, String search, LocalDate date) {
        if(search != null && date != null){
            return competitionRepository.findAllByCodeContainingOrLocationContainingAndDateEquals(search,search,date,pageable).getContent();
        } else if(search != null){
            return competitionRepository.findAllByCodeContainingOrLocationContaining(search,search,pageable).getContent();
        } else if(date != null){
            return competitionRepository.findAllByDateEquals(date,pageable).getContent();
        }
        return competitionRepository.findAll(pageable).getContent();
    }
    @Override
    public Competition getCompetitionById(String code) {
        return competitionRepository.findById(code).orElseThrow(() -> new ResourceNotFoundException("Competition not found"));
    }
    @Override
    public Competition createCompetition(Competition competition) {
        competition.setStartTime(LocalTime.of(8,00,0));
        competition.setEndTime(LocalTime.of(18,00,0));
        if(competition.getDate().isBefore(LocalDate.now())){
            throw new OperationException("Competition date cannot be in the past");
        }
        List<Competition> competitions = competitionRepository.findAllByDateEquals(competition.getDate(), Pageable.unpaged()).getContent();
        if(competitions.size()>0){
            throw new OperationException("Competition date cannot have more than 1 competition");
        }
        if(competition.getEndTime().isBefore(competition.getStartTime())){
            throw new OperationException("Competition start time cannot be greater than end time");
        }
        competition.setCode(competitionCodeGen.generateCode(competition.getLocation(),competition.getDate()));
        if(competitionRepository.existsById(competition.getCode())==true){
            throw new OperationException("Competition Code already exists");
        }
        return competitionRepository.save(competition);
    }
    @Override
    public Competition updateCompetition(Competition competition,String code) {
        getCompetitionById(code);
        if(competition.getDate().isBefore(LocalDate.now())){
            throw new OperationException("Competition date cannot be in the past");
        }
        List<Competition> competitions = competitionRepository.findAllByDateEquals(competition.getDate(), Pageable.unpaged()).getContent();
        competitions = competitions.stream().filter(competition1 -> competition1.getCode()!=code).toList();
        if ( competitions.size()>0){
            throw new OperationException("Competition date cannot have more than 1 competition");
        }
        if(competition.getEndTime().isBefore(competition.getStartTime())){
            throw new OperationException("Competition start time cannot be greater than end time");
        }
        competition.setCode(competitionCodeGen.generateCode(competition.getLocation(),competition.getDate()));
        List<Competition> competitions1 = competitionRepository.findAllByCodeEquals(competition.getCode(), Pageable.unpaged()).getContent();
        competitions1 = competitions1.stream().filter(competition1 -> competition1.getCode()!=code).toList();
        if(competitions1.size()>0){
            throw new OperationException("Competition Code already exists");
        }
        competition = competitionRepository.save(competition);
        deleteCompetition(code);
        return competition;
    }
    @Override
    public void deleteCompetition(String code) {
        if(competitionRepository.existsById(code)==true){
            competitionRepository.deleteById(code);
        }else{
            throw new ResourceNotFoundException("Competition does not exist");
        }
    }
    @Override
    public Boolean existsById(String code) {
        return competitionRepository.existsById(code);
    }
    @Override
    public Long countCompetitions(){
        return competitionRepository.count();
    }



}
