package com.noternal.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table
public class NoteEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "note_id")
    private Note note;

    @Column(columnDefinition="text")
    private String body;

    @Column
    private ZonedDateTime updated;

    @Column
    private boolean archived;

    public NoteEvent() {
    }

    public NoteEvent(Note note) {
        this.note = note;
        this.body = note.getBody();
        this.updated = note.getUpdated();
        this.archived = note.isArchived();
    }
}
