package com.aftas_backend.services.impl;

import com.aftas_backend.handlers.exceptionHandler.OperationException;
import com.aftas_backend.handlers.exceptionHandler.ResourceNotFoundException;
import com.aftas_backend.models.entities.Member;
import com.aftas_backend.repositories.MemberRepository;
import com.aftas_backend.services.MemberService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public List<Member> getAllMembers(Pageable pageable,String search) {
        if(search != null){
            return memberRepository.findAllByFirstNameOrLastNameOrNumbe(search,pageable).getContent();
        }
        return memberRepository.findAll(pageable).getContent();
    }
    @Override
    public Member getMemberByNumber(Integer number) {
        return memberRepository.findByNumber(number).orElseThrow(() -> new ResourceNotFoundException("Member not found"));
    }
    @Override
    public Member createMember(Member member) {
        if(memberRepository.findByNumber(member.getNumber()).isPresent()){
            throw new OperationException("Member Number already exists");
        }
        return memberRepository.save(member);
    }
    @Override
    public Member updateMember(Member member,Integer number) {
        if(memberRepository.existsById(number)==true){
            if(memberRepository.findByNumber(member.getNumber()).isPresent()){
                throw new OperationException("Member Number already exists");
            }
            member.setNumber(number);
            member = memberRepository.save(member);
        }else{
            throw new ResourceNotFoundException("Member does not exist");
        }
        return member;

    }
    @Override
    public void deleteMember(Integer number) {
        if(memberRepository.existsById(number)==true){
            memberRepository.deleteById(number);
        }else{
            throw new ResourceNotFoundException("Member does not exist");
        }
    }
    @Override
    public Boolean existsByNumber(Integer number) {
        return memberRepository.existsByNumber(number);
    }
    @Override
    public Long countMembers() {
        return memberRepository.count();
    }



}
