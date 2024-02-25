package com.aftas_backend.web.rest.vms.member;

import com.aftas_backend.models.entities.Member;

public record MemberResponseVM(
        String number,
        String first_name,
        String last_name,
        String nationality,
        String identity_document_type,
        String identity_number,
        String password
) {
    public static MemberResponseVM fromMember(Member member) {
        return new MemberResponseVM(
                member.getNumber().toString(),
                member.getFirstName(),
                member.getLastName(),
                member.getNationality(),
                member.getIdentityDocumentType().toString(),
                member.getIdentityNumber(),
                member.getPassword()
        );
    }
}
