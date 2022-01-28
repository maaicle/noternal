package com.noternal.app.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String body;

    @Column
    private ZonedDateTime updated;

    @Column
    private boolean archived;

    @Column
    private ZonedDateTime created;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "notes_tags",
        joinColumns = {
            @JoinColumn(name = "note_id", referencedColumnName = "id",
                nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "tag_id", referencedColumnName = "id",
                    nullable = false, updatable = false)})
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "note")
    private List<NoteEvent> noteEvents;

    public Note() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ZonedDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(ZonedDateTime updated) {
        this.updated = updated;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }
}
