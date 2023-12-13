package com.aftas_backend.repositories;

import com.aftas_backend.models.entities.Competition;
import com.aftas_backend.models.entities.RankId;
import com.aftas_backend.models.entities.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankId> {
    Boolean existsByMemberNumberAndCompetitionCode(Integer number, String competitionCode);
//     get ranking by competition code order by score des
    Page<Ranking> findAllByCompetitionCodeOrderByScoreDesc(String competitionCode,Pageable pageable);
//    get rankings by Member number order by score des
    Page<Ranking> findAllByMemberNumberOrderByScoreDesc(Integer number,Pageable pageable);
//    get rankings where competition code containing or Member number containing order by score des
    Page<Ranking> findAllByCompetitionCodeContainingOrMemberNumberContainingOrderByScoreDesc(String search1,String search2,Pageable pageable);
    Page<Ranking> findAllByMemberNumberAndCompetitionDate(Integer number, LocalDate date, Pageable pageable);



}
