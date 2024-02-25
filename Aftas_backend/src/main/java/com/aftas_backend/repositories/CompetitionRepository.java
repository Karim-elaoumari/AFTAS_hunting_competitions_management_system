package com.aftas_backend.repositories;

import com.aftas_backend.models.entities.Competition;
import com.aftas_backend.models.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;
import java.time.LocalDate;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, String>{
    Page<Competition> findAllByCodeContainingOrLocationContainingAndDateEquals(String search1,String search2, LocalDate date, Pageable pageable);
    Page<Competition> findAllByCodeContainingOrLocationContaining(String search1,String search2, Pageable pageable);
    Page<Competition> findAllByDateEquals(LocalDate date, Pageable pageable);
    Page<Competition> findAllByCodeEquals(String code, Pageable pageable);


}
