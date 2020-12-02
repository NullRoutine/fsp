package com.example.fsp.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetail extends User {
    private UserBean userEntity;

    public UserBean getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserBean userEntity) {
        this.userEntity = userEntity;
    }

    public UserDetail(UserBean userEntity, Collection<? extends GrantedAuthority> authorities) {
        super(userEntity.getName(), userEntity.getPassword(), authorities);
        this.userEntity = userEntity;
    }
}
