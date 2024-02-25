package com.aftas_backend.models.entities;

import com.aftas_backend.models.enums.IdentityDocumentType;
import com.aftas_backend.models.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    @Column(unique = true)
    private Integer number;

    private String firstName;

    private String lastName;

    private String password;

    private String nationality;

    private IdentityDocumentType identityDocumentType;

    private String identityNumber;

    private String role;


    @OneToMany(mappedBy = "member")
    private List<Hunting> huntings;


    @OneToMany(mappedBy = "member")
    private List<Ranking> rankings;


    @CreationTimestamp
    private LocalDateTime createdAt;


    @UpdateTimestamp
    private LocalDateTime modifiedAt;


}
