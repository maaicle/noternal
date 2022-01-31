package com.noternal.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "notes")
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
    @JsonBackReference
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "note")
    private List<NoteEvent> noteEvents;

    public Note() {
    }

    public Note(String body, Set<Tag> tags) {
        this.body = body;
        this.tags = tags;
        this.updated = ZonedDateTime.now();
        this.archived = false;
        this.created = ZonedDateTime.now();
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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTags(Set<Tag> tags) {
        this.tags.addAll(tags);
    }

    public void removeTags(Set<Tag> tags) {
        this.tags.removeAll(tags);
    }

    public void updateNote(Long id, String body, boolean archived) {
        this.body = body;
        this.tags = tags;
        this.updated = ZonedDateTime.now();
        this.archived = archived;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", tags=" + tags +
                '}';
    }
}
