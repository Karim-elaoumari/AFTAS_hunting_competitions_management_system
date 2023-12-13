package com.aftas_backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HuntingDTO {
    private Double weight;
    private Long fish_id;
    private String competition_code;
    private Integer member_number;
}
