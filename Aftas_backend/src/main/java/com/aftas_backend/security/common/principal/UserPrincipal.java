package com.aftas_backend.security.common.principal;

import com.aftas_backend.models.entities.Hunting;
import com.aftas_backend.models.entities.Member;
import com.aftas_backend.models.entities.Ranking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class UserPrincipal extends Member implements UserDetails {

    @JsonIgnore
    private List<Hunting> huntings;
    @JsonIgnore
    private List<Ranking> rankings;
    @JsonIgnore
    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_" + getRole());
    }

    @Override
    public String getUsername() {
        return getNumber().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setMember(@NotNull Member member) {
        setFirstName(member.getFirstName());
        setLastName(member.getLastName());
        setRole(member.getRole());
        setNumber(member.getNumber());
        setPassword(member.getPassword());
        setCreatedAt(member.getCreatedAt());
        setModifiedAt(member.getModifiedAt());
        setIdentityDocumentType(member.getIdentityDocumentType());
        setIdentityNumber(member.getIdentityNumber());
        setNationality(member.getNationality());
    }


}
