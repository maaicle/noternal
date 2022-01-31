package com.noternal.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "user_id")
//    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String view;

    @Column
    private String type;

    @Column
    private String value;

    @Column
    private ZonedDateTime created;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Note> notes = new HashSet<>();

    public Tag() {
    }

    public Tag(User user, String view, String type, String value) {
        this.user = user;
        this.view = view;
        this.type = type;
        this.value = value;
        this.created = ZonedDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return user.equals(tag.user) && type.equals(tag.type) && value.equals(tag.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, type, value);
    }
}
