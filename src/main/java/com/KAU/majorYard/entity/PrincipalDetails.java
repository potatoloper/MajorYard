package com.KAU.majorYard.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PrincipalDetails implements UserDetails {
    private final User user;
    private final Collection<GrantedAuthority> authorities;

    public PrincipalDetails(User user, Collection<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = new ArrayList<>(authorities);  // 기존 권한을 리스트에 추가
        this.authorities.add(new SimpleGrantedAuthority( user.getRole().name()));  // 새 권한 추가
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(new ArrayList<>(authorities));  // 불변 리스트 반환
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // TODO: 데이터베이스 조회 로직 필요
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // TODO: 데이터베이스 조회 로직 필요
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // TODO: 데이터베이스 조회 로직 필요
    }

    @Override
    public boolean isEnabled() {
        return true;  // TODO: 데이터베이스 조회 로직 필요
    }
}
