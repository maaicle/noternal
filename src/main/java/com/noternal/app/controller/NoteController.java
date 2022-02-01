package com.noternal.app.controller;

import com.noternal.app.entity.Note;
import com.noternal.app.model.NoteDto;
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

    @GetMapping("/notes/all")
    public List<NoteDto> getAllNotes() {
//        return noteRepository.findAllByOrderByIdAsc();
//        return noteRepository.findAll();
        return noteService.getAllNotesDto();
    }

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
        Optional<NoteDto> noteDto;
        //            note = noteRepository.findById(id.get());
        noteDto = noteService.getNoteDto(id.get());
        if (noteDto.isEmpty()){
            return Optional.of("Note does not exist");
        }
        return noteDto;
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

        List<String> listParts = new ArrayList<>();
        String[] strParts = body.get("tagValues").split(",");
        if (strParts.length > 0) {
            listParts = Arrays.asList(strParts);
        }
        Set<String> tagValues = new HashSet<>(listParts);
        noteService.updateNote(id, noteBody, tagValues, archived);
        return "Note Updated";
    }

}
