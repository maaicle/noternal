package com.noternal.app.service;

import com.noternal.app.entity.Note;
import com.noternal.app.entity.Tag;
import com.noternal.app.entity.User;
import com.noternal.app.model.NoteDto;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface NoteService {
    void addNote(String body, Set<String> tagValues);
    List<NoteDto> getAllNotesDto();
    Optional<NoteDto> getNoteDto(long id);
    void addTagsToNote(long id, Set<String> tagValues);
    void removeTagsFromNote(long noteId, Set<String> tagValues);
    void updateNote(long noteId, String body, Set<String> tagValues, boolean archived);
}
