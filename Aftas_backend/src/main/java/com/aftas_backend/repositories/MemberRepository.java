package com.aftas_backend.repositories;

import com.aftas_backend.models.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query(value = "SELECT m FROM Member m WHERE " +
            "m.firstName LIKE %:search% OR " +
            "m.lastName LIKE %:search% OR " +
            "CAST(m.number AS string) LIKE %:search%")
    Page<Member> findAllByFirstNameOrLastNameOrNumber(
            @Param("search") String search,
            Pageable pageable);

    Optional<Member> findByNumber(Integer number);

    Boolean existsByNumber(Integer number);
}
