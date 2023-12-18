package com.aftas_backend.services.impl;

import com.aftas_backend.models.entities.Competition;
import com.aftas_backend.models.entities.Ranking;
import com.aftas_backend.repositories.RankingRepository;
import com.aftas_backend.services.CompetitionService;
import com.aftas_backend.services.MemberService;
import com.aftas_backend.services.RankingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RankingServiceImplTest {


        private RankingService rankingService;
        private RankingRepository rankingRepository;
        private MemberService memberService;
        private CompetitionService competitionService;

        @BeforeEach
        void setUp() {
            rankingRepository = Mockito.mock(RankingRepository.class);
            memberService = Mockito.mock(MemberService.class);
            competitionService = Mockito.mock(CompetitionService.class);
            rankingService = new RankingServiceImpl(rankingRepository, memberService, competitionService);
        }

        @Test
        public void test_updateAllRankings_returnUpdatedRankings() {

            Ranking ranking = new Ranking();
            ranking.setCompetition(new Competition());
            ranking.getCompetition().setCode("COMP1");
            ranking.setScore(10.0);

            Ranking ranking1 = new Ranking();
            ranking1.setCompetition(new Competition());
            ranking1.getCompetition().setCode("COMP1");
            ranking1.setScore(20.0);

            List<Ranking> rankings = new ArrayList<>();
            rankings.add(ranking);
            rankings.add(ranking1);
            Pageable pageable = Pageable.unpaged();

            Mockito.when(rankingRepository.findAllByCompetitionCodeOrderByScoreDesc("COMP1",pageable)).thenReturn(new PageImpl<>(rankings, Pageable.unpaged(), rankings.size()));


            List<Ranking> updatedRankings = rankingService.updateAllRankings(ranking);


            assertEquals(2, updatedRankings.size());
            assertEquals(ranking, updatedRankings.get(0));
            assertEquals(ranking1, updatedRankings.get(1));
        }

        // should return an empty list if no rankings exist for the given competition code
        @Test
        public void test_updateAllRankings_returnEmptyListIfNoRankingsExist() {

            List<Ranking> rankings = new ArrayList<>();
            Pageable pageable = Pageable.unpaged();

            Mockito.when(rankingRepository.findAllByCompetitionCodeOrderByScoreDesc("COMP1", pageable)).thenReturn(new PageImpl<>(new ArrayList<>(), Pageable.unpaged(), 0));


            List<Ranking> updatedRankings = rankingService.updateAllRankings(Ranking.builder().competition(Competition.builder().code("COMP1").build()).build());


            assertTrue(updatedRankings.isEmpty());
        }


        @Test
        public void test_updateAllRankings_handleCaseWithSameScore() {

            Ranking ranking = new Ranking();
            ranking.setCompetition(new Competition());
            ranking.getCompetition().setCode("COMP1");
            ranking.setScore(10.0);

            Ranking ranking1 = new Ranking();
            ranking1.setCompetition(new Competition());
            ranking1.getCompetition().setCode("COMP1");
            ranking1.setScore(20.0);

            Ranking ranking2 = new Ranking();
            ranking2.setCompetition(new Competition());
            ranking2.getCompetition().setCode("COMP1");
            ranking2.setScore(20.0);

            List<Ranking> rankings = new ArrayList<>();
            rankings.add(ranking);
            rankings.add(ranking1);
            rankings.add(ranking2);

            Mockito.when(rankingRepository.findAllByCompetitionCodeOrderByScoreDesc("COMP1", Pageable.unpaged())).thenReturn(new PageImpl<>(rankings, Pageable.unpaged(), rankings.size()));


            List<Ranking> updatedRankings = rankingService.updateAllRankings(ranking);


            assertEquals(3, updatedRankings.size());
            assertEquals(1, updatedRankings.get(0).getRank());
            assertEquals(2, updatedRankings.get(1).getRank());
            assertEquals(3, updatedRankings.get(2).getRank());
        }


}