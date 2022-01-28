package com.noternal.app.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
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

//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

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
}
