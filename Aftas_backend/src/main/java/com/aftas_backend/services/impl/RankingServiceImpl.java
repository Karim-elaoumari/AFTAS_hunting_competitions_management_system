package com.aftas_backend.services.impl;

import com.aftas_backend.handlers.exceptionHandler.OperationException;
import com.aftas_backend.handlers.exceptionHandler.ResourceNotFoundException;
import com.aftas_backend.models.entities.Competition;
import com.aftas_backend.models.entities.Member;
import com.aftas_backend.models.entities.RankId;
import com.aftas_backend.models.entities.Ranking;
import com.aftas_backend.repositories.RankingRepository;
import com.aftas_backend.security.common.principal.UserPrincipalService;
import com.aftas_backend.services.CompetitionService;
import com.aftas_backend.services.MemberService;
import com.aftas_backend.services.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {
    private final RankingRepository rankingRepository;
    private final MemberService memberService;
    private final UserPrincipalService userPrincipalService;
    private final CompetitionService competitionService;

    @Override
    public Ranking createRanking(Ranking ranking) {
        Member member = memberService.getMemberByNumber(ranking.getMember().getNumber());
        Competition competition = competitionService.getCompetitionById(ranking.getCompetition().getCode());
        Boolean exists = existsByMemberNumberAndCompetitionCode(member.getNumber(),competition.getCode());
        if(competition.getMaxParticipants()<competition.getRankings().size()+1){
            throw new OperationException("Competition is full");
        }
        if(exists==true){
            throw new OperationException("Ranking already exists");
        }
//        check if member all ready have a ranking competition in the same data as the new ranking
        List<Ranking> rankings = rankingRepository.findAllByMemberNumberAndCompetitionDate(member.getNumber(),competition.getDate(), Pageable.unpaged()).getContent();
        if(rankings.size()>0){
            throw new OperationException("Member already have a ranking in the same date");
        }
//        check id competition date is greater than now with 24  hours if not throw exception
        if(competition.getDate().isBefore(LocalDate.now().plusDays(1))){
            throw new OperationException("No ranking can be created for a competition that is less than 24 hours from now");
        }
        ranking.setMember(member);
        ranking.setCompetition(competition);
        ranking.setScore(0.0);
        ranking.setRank(competition.getRankings().size()+1);
        return rankingRepository.save(ranking);
    }
    @Override
    public void deleteRankingByMemberNumberAndCompetitionCode(Integer number, String competitionCode) {
        if(rankingRepository.existsByMemberNumberAndCompetitionCode(number,competitionCode)==false){
            throw new ResourceNotFoundException("Ranking does not exist");
        }
        RankId rankId = new RankId(number,competitionCode);
        rankingRepository.deleteById(rankId);
    }
    @Override
    public Boolean existsByMemberNumberAndCompetitionCode(Integer number, String competitionCode) {
        RankId rankId = new RankId(number,competitionCode);
        return rankingRepository.existsById(rankId);
    }
    @Override
    public Ranking findRankingByMemberNumberAndCompetitionCode(Integer number, String competitionCode) {
        RankId rankId = new RankId(number,competitionCode);
        return rankingRepository.findById(rankId).orElseThrow(() -> new ResourceNotFoundException("Ranking does not exist"));
    }
    @Override
    public Ranking updateRanking(Ranking ranking) {
        Ranking ranking1 = findRankingByMemberNumberAndCompetitionCode(ranking.getMember().getNumber(),ranking.getCompetition().getCode());
        ranking1.setScore(ranking.getScore());
        ranking1 =  rankingRepository.save(ranking1);
        List<Ranking> rankings = updateAllRankings(ranking1);
        return rankings.get(rankings.indexOf(ranking1));
    }
    @Override
    public List<Ranking> getAllRankings(Pageable pageable, String search){
        if(search != null){
            return rankingRepository.findAllByCompetitionCodeContainingOrMemberNumberContainingOrderByScoreDesc(search,search,pageable).getContent();
        }
        return rankingRepository.findAll(pageable).getContent();
    }
    @Override
    public List<Ranking> getAllRankingsByMemberNumber(Pageable pageable, Integer number){
        return rankingRepository.findAllByMemberNumberOrderByScoreDesc(number,pageable).getContent();
    }

    @Override
    public List<Ranking> getAllRankingsByCompetitionCode(Pageable pageable, String competitionCode) {
        return rankingRepository.findAllByCompetitionCodeOrderByScoreDesc(competitionCode, pageable).getContent();
    }

    @Override
    public List<Ranking> updateAllRankings(Ranking ranking) {
        List<Ranking> rankings = rankingRepository.findAllByCompetitionCodeOrderByScoreDesc(ranking.getCompetition().getCode(), Pageable.unpaged()).getContent();
        List<Ranking> rankings1 = new ArrayList<>();
        for (Ranking ranking1 : rankings) {
            ranking1.setRank(rankings.indexOf(ranking1) + 1);
            rankings1.add(ranking1);
        }
        rankingRepository.saveAll(rankings1);
        return rankings1;
    }

    @Override
    public List<Competition> getMyCompetitions(Pageable pageable) {
        Integer number = userPrincipalService.getUserPrincipalFromContextHolder().getNumber();
        List<Ranking> rankings = rankingRepository.findAllByMemberNumberOrderByScoreDesc(number, pageable).getContent();
        List<Competition> competitions = new ArrayList<>();
        for (Ranking ranking : rankings) {
            competitions.add(ranking.getCompetition());
        }
        return competitions;
    }

    @Override
    public List<Competition> getMyTodayCompetitions(Pageable pageable) {

        Integer number = userPrincipalService.getUserPrincipalFromContextHolder().getNumber();
        List<Ranking> rankings  =  rankingRepository.findAllByMemberNumberOrderByScoreDesc(number, pageable).getContent();
        List<Competition> competitions = new ArrayList<>();
        for (Ranking ranking : rankings) {
            if(ranking.getCompetition().getDate().isEqual(LocalDate.now())){
                competitions.add(ranking.getCompetition());
            }
        }
        return  competitions;

    }

}
