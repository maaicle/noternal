package com.noternal.app.model;

import javax.persistence.*;

@Entity
@Table(name="users")
@Access(AccessType.FIELD)
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("id= %d, username= %s", id, username);
    }
}
