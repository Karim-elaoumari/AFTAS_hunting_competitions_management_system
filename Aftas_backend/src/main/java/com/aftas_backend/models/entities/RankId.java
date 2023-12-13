package com.aftas_backend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class RankId implements Serializable  {


    @Column(name = "member_id")
    private Integer memberNumber;

    @Column(name = "competition_id")
    private String competitionCode;

}
