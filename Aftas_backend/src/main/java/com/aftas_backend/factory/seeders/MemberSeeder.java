package com.aftas_backend.factory.seeders;

import com.aftas_backend.factory.fakers.MemberFaker;
import com.aftas_backend.models.entities.Member;
import com.aftas_backend.repositories.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberSeeder {
    private final MemberRepository memberRepository;
    private final MemberFaker memberFaker;
    public MemberSeeder(MemberRepository memberRepository, MemberFaker memberFaker) {
        this.memberRepository = memberRepository;
        this.memberFaker = memberFaker;
    }
    public void seed(Integer count) {
        List<Member> members = new  ArrayList<>();
        for (int i = 0; i < count; i++) {
            members.add(memberFaker.makeMember());
        }
        memberRepository.saveAll(members);
    }
}
