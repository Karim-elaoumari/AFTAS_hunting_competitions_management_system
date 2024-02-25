package com.aftas_backend.factory.fakers;

import com.aftas_backend.models.entities.Member;
import com.aftas_backend.models.enums.IdentityDocumentType;
import com.aftas_backend.models.enums.Roles;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class MemberFaker {
        private final Faker faker;
        private final PasswordEncoder passwordEncoder;
        public MemberFaker(PasswordEncoder passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
            this.faker = new Faker();
        }
        public Member makeMember(){
            return Member.builder()
                    .number(faker.number().numberBetween(1, 1000))
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .nationality(faker.address().country())
                    .identityDocumentType(faker.options().option(IdentityDocumentType.CIN, IdentityDocumentType.PASSPORT, IdentityDocumentType.RESIDENCE_CARD, IdentityDocumentType.PERMIT))
                    .identityNumber(faker.number().digits(8))
                    .password(passwordEncoder.encode("123456"))
                    .role(Roles.ADHERENT.name())
                    .build();

        }
}
