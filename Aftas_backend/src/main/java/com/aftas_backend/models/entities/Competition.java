package com.aftas_backend.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Competition {
    @Id
    private String code;
    private LocalDate date;
    private String location;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double amount;
    private Integer maxParticipants;
    private String description;
    @OneToMany(mappedBy = "competition")
    private List<Hunting> huntings;
    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

}
