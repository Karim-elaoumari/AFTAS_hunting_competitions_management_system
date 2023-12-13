package com.aftas_backend.web.rest.controllers;

import com.aftas_backend.handlers.response.ResponseMessage;
import com.aftas_backend.models.entities.Ranking;
import com.aftas_backend.services.RankingService;
import com.aftas_backend.web.rest.vms.ranking.RankingRequestCreateVM;
import com.aftas_backend.web.rest.vms.ranking.RankingResponseVM;
import jakarta.validation.Valid;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rankings")
public class RankingController {
    private final RankingService rankingService;
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }
    @GetMapping
    public ResponseEntity getAllRankings(@ParameterObject Pageable pageable, @RequestParam(required = false, name = "search") String search) {
        List<Ranking> rankings = rankingService.getAllRankings(pageable, search);
        List<RankingResponseVM> rankingResponseVMS = new ArrayList<>();
        for(Ranking ranking:rankings){
            rankingResponseVMS.add(RankingResponseVM.fromRanking(ranking));
        }
        return ResponseMessage.ok(rankingResponseVMS,"Rankings retrieved successfully");
    }
    @GetMapping("/competition/{code}")
    public ResponseEntity getAllRankingsByCompetition(@ParameterObject Pageable pageable,@PathVariable String code) {
        List<Ranking> rankings = rankingService.getAllRankingsByCompetitionCode(pageable, code);
        List<RankingResponseVM> rankingResponseVMS = new ArrayList<>();
        for(Ranking ranking:rankings){
            rankingResponseVMS.add(RankingResponseVM.fromRanking(ranking));
        }
        return ResponseMessage.ok(rankingResponseVMS,"Rankings retrieved successfully");
    }
    @GetMapping("/member/{number}")
    public ResponseEntity getAllRankingsByMember(@ParameterObject Pageable pageable, @PathVariable Integer number) {
        List<Ranking> rankings = rankingService.getAllRankingsByMemberNumber(pageable, number);
        List<RankingResponseVM> rankingResponseVMS = new ArrayList<>();
        for(Ranking ranking:rankings){
            rankingResponseVMS.add(RankingResponseVM.fromRanking(ranking));
        }
        return ResponseMessage.ok(rankingResponseVMS,"Rankings retrieved successfully");
    }
    @GetMapping("/competition/{code}/member/{number}")
    public ResponseEntity getAllRankingsByCompetitionAndMember(@ParameterObject Pageable pageable, @PathVariable String code, @PathVariable Integer number) {
        Ranking ranking = rankingService.findRankingByMemberNumberAndCompetitionCode(number,code);
        return ResponseMessage.ok(RankingResponseVM.fromRanking(ranking),"Ranking retrieved successfully");
    }
    @PostMapping()
    public ResponseEntity createRanking(@Valid @RequestBody RankingRequestCreateVM rankingVM) {
        Ranking createdRanking = rankingService.createRanking(rankingVM.toRanking());
        RankingResponseVM rankingResponseVM = RankingResponseVM.fromRanking(createdRanking);
        return ResponseMessage.created(rankingResponseVM,"Ranking created successfully");
    }
    @DeleteMapping("/{number}/{code}")
    public ResponseEntity deleteRanking(@PathVariable Integer number, @PathVariable String code) {
        rankingService.deleteRankingByMemberNumberAndCompetitionCode(number,code);
        return ResponseMessage.ok(null,"Ranking deleted successfully");
    }



}
