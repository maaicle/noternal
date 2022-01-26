package com.noternal.app.entity;

import com.noternal.app.model.UserDto;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass_hash")
    private String passHash;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "account_non_locked")
    private String accountNonLocked;

    @Column(name = "created")
    private Timestamp created;

    public User() {
    }

    public User(String userName, String passHash) {
        this.userName = userName;
        this.passHash = passHash;
    }

    public User(UserDto userDto){
        this.userName = userDto.getUserName();
        this.passHash = userDto.getPassHash();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    @Override
    public String toString() {
        return String.format("id= %d, username= %s", id, userName);
    }
}
