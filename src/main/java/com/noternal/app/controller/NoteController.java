package com.noternal.app.controller;

import com.noternal.app.entity.Note;
import com.noternal.app.repository.NoteRepository;
import com.noternal.app.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    NoteRepository noteRepository;

    @PostMapping("/notes")
    public String addNote(@RequestBody Map<String, String> body) {
        String noteBody = body.get("body");

        String[] strParts = body.get("tagValues").split(",");
        List<String> listParts = Arrays.asList(strParts);
        Set<String> tagValues = new HashSet<>(listParts);

        noteService.addNote(noteBody, tagValues);
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
        noteService.addTagsToNote(id.get(), tagValues);
        return "Tags added to note";
    }

    @DeleteMapping("/notes/{id}/remove-tags")
    public String removeTags(@PathVariable Optional<Long> id, @RequestBody Set<String> tagValues) {
        noteService.removeTagsFromNote(id.get(), tagValues);
        return "Tags removed from note";
    }

    @PutMapping("/notes/{id}")
    public String updateNote(@PathVariable long id, @RequestBody Map<String, String> body) {
        String noteBody = body.get("body");
        boolean archived = Boolean.parseBoolean(body.get("archived"));

        String[] strParts = body.get("tagValues").split(",");
        List<String> listParts = Arrays.asList(strParts);
        Set<String> tagValues = new HashSet<>(listParts);
        noteService.updateNote(id, noteBody, tagValues, archived);
        return "Note Updated";
    }

}
