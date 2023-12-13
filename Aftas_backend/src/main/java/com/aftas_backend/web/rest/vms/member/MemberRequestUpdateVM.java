package com.aftas_backend.web.rest.vms.member;

import com.aftas_backend.models.entities.Member;
import com.aftas_backend.models.enums.IdentityDocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MemberRequestUpdateVM(
    @NotBlank(message = "First name is required")
    String first_name,
    @NotBlank(message = "Last name is required")
    String last_name,
    @NotBlank(message= "nationality is required")
    String nationality,
    @NotBlank(message = "Identity document type is required")
    @Pattern(regexp = "^(CIN| PASSPORT| PERMIT| RESIDENCE_CARD)$", message = "Identity document type is invalid")
    String identity_document_type,
    @NotBlank(message = "Identity  number is required")
    String identity_number
) {
        public Member toMember(){
            return Member.builder()
                    .firstName(first_name)
                    .lastName(last_name)
                    .identityDocumentType(IdentityDocumentType.valueOf(identity_document_type))
                    .identityNumber(identity_number)
                    .nationality(nationality)
                    .build();
        }
    }
