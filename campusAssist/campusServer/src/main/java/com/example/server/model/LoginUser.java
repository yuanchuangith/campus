package com.example.server.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.server.model.view.accountEntity.Userinfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor

public class LoginUser implements UserDetails {

    public LoginUser(Userinfo user, List<String> auths) {
        this.user = user;
        this.auths = auths;
    }

    private Userinfo user;

    private List<String> auths;

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities !=null)
        {
            return authorities;
        }

        List<SimpleGrantedAuthority> collect = auths.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassWord();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
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
}
