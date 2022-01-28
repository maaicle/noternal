package com.noternal.app.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table
public class NoteEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "note_id")
    private long noteId;

    @Column
    private String body;

    @Column
    private ZonedDateTime updated;

    @Column
    private boolean archived;
}
