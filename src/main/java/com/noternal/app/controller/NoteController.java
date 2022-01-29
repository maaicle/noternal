package com.noternal.app.controller;

import com.noternal.app.entity.Note;
import com.noternal.app.entity.Tag;
import com.noternal.app.repository.NoteRepository;
import com.noternal.app.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    NoteRepository noteRepository;

    @PostMapping("/notes")
    public String addNote(@RequestBody Note note) {
        noteService.addNote(note.getBody());
        return "Note Created";
    }

    @GetMapping("/notes/{id}")
    public Optional<? extends Object> getNote(@PathVariable Optional<Long> id) {
        if (id.isEmpty()) {
            return Optional.of("Must provide note id");
        }
        Optional<Note> note = Optional.empty();
        if(id.isPresent()) {
            note = noteRepository.findById(id.get());
            if (note.isEmpty()){
                return Optional.of("Note does not exist");
            }
        }
        return note;
    }

    @PostMapping("/notes/{id}/add-tags")
    public String addTags(@PathVariable Optional<Long> id, @RequestBody Set<String> tagValues) {
        System.out.println("NoteController | addTags" + id.get());
        System.out.println("NoteController | addTags" + tagValues);
        noteService.addTagsToNote(id.get(), tagValues);
        return "Tags added to note";
    }


}
