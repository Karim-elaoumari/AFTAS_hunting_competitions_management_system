package com.aftas_backend.web.rest.vms.ranking;

import com.aftas_backend.models.entities.Competition;
import com.aftas_backend.models.entities.Member;
import com.aftas_backend.models.entities.Ranking;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RankingRequestCreateVM(
        @NotNull(message = "Member number is required")
        @DecimalMin(value = "1", message = "Member number must be greater than 0")
        Integer member_number,
        @NotBlank(message = "Competition code is required")
        String competition_code
) {
    public Ranking toRanking() {
        return Ranking.builder()
                .member(Member.builder()
                        .number(member_number)
                        .build())
                .competition(Competition.builder()
                        .code(competition_code)
                        .build())
                .build();
    }
}
