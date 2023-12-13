package com.aftas_backend.repositories;

import com.aftas_backend.models.entities.Hunting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, String>{
    void deleteById(String id);
    Page<Hunting> findAllByMember_Number(Integer memberNumber, Pageable pageable);
    Page<Hunting> findAllByMember_NumberAndCompetition_Code(Integer memberNumber, String competitionCode, Pageable pageable);
    Page<Hunting> findAllByCompetition_Code(String competitionCode, Pageable pageable);

    Optional<Hunting> findByMember_NumberAndFish_IdAndCompetition_Code(Integer memberNumber, Long fishId, String competitionCode);
    Boolean existsByMember_NumberAndFish_IdAndCompetition_Code(Integer memberNumber, Long fishId, String competitionCode);
}
