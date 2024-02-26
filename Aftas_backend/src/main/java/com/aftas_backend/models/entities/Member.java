package com.aftas_backend.models.entities;

import com.aftas_backend.models.enums.IdentityDocumentType;
import com.aftas_backend.models.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
    @ColumnDefault("false")
    @Getter
    @Setter
    @Column(name = "is_member_activated")
    private Boolean isMemberActivated;
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
