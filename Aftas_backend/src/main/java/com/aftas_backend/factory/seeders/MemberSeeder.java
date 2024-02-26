package com.aftas_backend.factory.seeders;

import com.aftas_backend.factory.fakers.MemberFaker;
import com.aftas_backend.models.entities.Member;
import com.aftas_backend.models.enums.IdentityDocumentType;
import com.aftas_backend.models.enums.Roles;
import com.aftas_backend.repositories.MemberRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberSeeder {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberFaker memberFaker;
    private final Faker faker = new Faker();

    public void seed(Integer count) {
        List<Member> members = new ArrayList<>();
        members.add(getAdminManager());
        members.add(getJuryMember());
        for (int i = 0; i < count; i++) {
            members.add(memberFaker.makeMember());
        }
        memberRepository.saveAll(members);
    }

    private Member getAdminManager() {
        return Member.builder()
                .number(1)
                .firstName("abdellah")
                .lastName("el hilaly")
                .role(String.valueOf(Roles.MANAGER))
                .password(passwordEncoder.encode("123456"))
                .nationality(faker.address().country())
                .identityDocumentType(IdentityDocumentType.CIN)
                .identityNumber(faker.number().digits(8))
                .isMemberActivated(true)
                .build();
    }
    private Member getJuryMember() {
        return Member.builder()
                .number(2)
                .firstName("karim")
                .lastName("el oumari")
                .role(String.valueOf(Roles.JURY))
                .password(passwordEncoder.encode("123456"))
                .nationality(faker.address().country())
                .identityDocumentType(IdentityDocumentType.CIN)
                .identityNumber(faker.number().digits(8))
                .isMemberActivated(true)
                .build();
    }


}
