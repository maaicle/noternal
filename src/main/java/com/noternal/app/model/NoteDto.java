package com.noternal.app.model;

import com.noternal.app.entity.NoteEvent;
import com.noternal.app.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteDto {
    private long id;
    private String body;
    private ZonedDateTime updated;
    private boolean archived;
    private ZonedDateTime created;
    private Set<String> tags = new HashSet<>();
    private List<NoteEvent> noteEvents;

    public NoteDto(long id, String body, ZonedDateTime updated, boolean archived, ZonedDateTime created, Set<String> tags) {
        this.id = id;
        this.body = body;
        this.updated = updated;
        this.archived = archived;
        this.created = created;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public ZonedDateTime getUpdated() {
        return updated;
    }

    public boolean isArchived() {
        return archived;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public Set<String> getTags() {
        return tags;
    }

    public List<NoteEvent> getNoteEvents() {
        return noteEvents;
    }
}