package com.noternal.app.service;

import com.noternal.app.entity.Note;
import com.noternal.app.entity.NoteEvent;
import com.noternal.app.repository.NoteEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteEventServiceImpl implements NoteEventService{

    @Autowired
    private NoteEventRepository noteEventRepository;

    @Override
    public void addNoteEvent(Note note) {
        NoteEvent noteEvent = new NoteEvent(note);
        noteEventRepository.save(noteEvent);
    }
}
