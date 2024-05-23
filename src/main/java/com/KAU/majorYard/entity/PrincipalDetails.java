package com.KAU.majorYard.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


/* 사용자 인증 과정에서 Spring Security에 의해 사용되며, 사용자 계정의 상태와 권한을 관리 */
/* UserDetails는 Spring Security에서 사용자의 정보를 담는 인터페이스. */
public class PrincipalDetails implements UserDetails {
    private final User user;
    private final Collection<GrantedAuthority> authorities;

    public PrincipalDetails(User user, Collection<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = new ArrayList<>();
        this.authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return true;  // 계정 만료 여부 로직
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // 계정 잠김 여부 로직
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // 자격 증명 만료 여부 로직
    }

    @Override
    public boolean isEnabled() {
        return true;  // 계정 활성화 여부 로직
    }
}
