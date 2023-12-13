package com.aftas_backend.web.rest.vms.hunting;

import com.aftas_backend.models.dto.HuntingDTO;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

public record HuntingRequestVM(
        @NotNull(message = "Fish weight is required")
        @DecimalMin(value = "0.1", message = "Fish weight must be greater than 0")
        Double weight,
        @NotNull(message = "Fish id is required")
        Long fish_id,
        @NotNull(message = "Competition code is required")
        String competition_code,
        @NotNull(message = "Member id is required")
        @Min(value = 1, message = "Member id must be greater than 0")
        Integer member_number
) {
    public HuntingDTO toHuntingDTO() {
        return HuntingDTO.builder()
                .weight(weight)
                .fish_id(fish_id)
                .competition_code(competition_code)
                .member_number(member_number)
                .build();
    }
}
