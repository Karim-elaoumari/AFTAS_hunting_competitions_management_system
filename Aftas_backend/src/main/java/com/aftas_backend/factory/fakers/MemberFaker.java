package com.aftas_backend.factory.fakers;

import com.aftas_backend.models.entities.Member;
import com.aftas_backend.models.enums.IdentityDocumentType;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class MemberFaker {
        private Faker faker;
        public MemberFaker() {
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
                    .build();

        }
}
