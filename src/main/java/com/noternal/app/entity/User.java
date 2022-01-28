package com.noternal.app.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "created")
    private ZonedDateTime created;

    @OneToMany(mappedBy = "user")
    private List<Tag> tags;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.created = ZonedDateTime.now();
    }

//    public User(String username, String password, boolean accountNonLocked) {
//        this.username = username;
//        this.password = password;
//        this.accountNonLocked = accountNonLocked;
//    }
//
//
//
//    public User(UserDto userDto){
//        this.username = userDto.getUserName();
//        this.password = userDto.getPassHash();
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    public boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public String toString() {
        return String.format("id= %d, username= %s", id, username);
    }
}
