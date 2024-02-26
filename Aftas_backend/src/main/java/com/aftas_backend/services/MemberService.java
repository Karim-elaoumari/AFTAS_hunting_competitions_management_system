package com.aftas_backend.services;

import com.aftas_backend.models.entities.Member;
import com.aftas_backend.models.enums.Roles;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {


    List<Member> getAllMembers(Pageable pageable, String search);
    Member getMemberByNumber(Integer number);
    Member createMember(Member member, Roles adherent);
    Member updateMember(Member member,Integer number);
    void deleteMember(Integer number);
    Boolean existsByNumber(Integer number);
    Long countMembers();
    Boolean activateMember(Integer number);

}
