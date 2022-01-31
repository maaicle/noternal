package com.noternal.app.service;

import com.noternal.app.entity.Note;
import com.noternal.app.entity.Tag;
import com.noternal.app.entity.User;


import java.util.List;
import java.util.Set;

public interface NoteService {
    void addNote(String body, Set<String> tagValues);
    List<Note> getAllNotes();
    void addTagsToNote(long id, Set<String> tagValues);
    void removeTagsFromNote(long noteId, Set<String> tagValues);
//    User getUserFromTag(Note note);
    void updateNote(long noteId, String body, Set<String> tagValues, boolean archived);
}
