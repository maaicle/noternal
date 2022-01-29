package com.noternal.app.service;

import com.noternal.app.entity.Note;
import com.noternal.app.entity.Tag;


import java.util.List;
import java.util.Set;

public interface NoteService {
    void addNote(String body);
    List<Note> getAllNotes();
    void addTagsToNote(long id, Set<String> tagValues);
}
