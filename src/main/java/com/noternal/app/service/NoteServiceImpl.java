package com.noternal.app.service;

import com.noternal.app.entity.Note;
import com.noternal.app.entity.Tag;
import com.noternal.app.entity.User;
import com.noternal.app.repository.NoteRepository;
import com.noternal.app.repository.TagRepository;
import com.noternal.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagService tagService;

    @Override
    public void addNote(String body) {
        User user = userRepository.getById(1L);
        Set<Tag> userTag = tagRepository.findByUserAndTypeAndValue(user, "user", user.getUsername());
        Note note = new Note(body, userTag);
        noteRepository.save(note);
    }

    @Override
    public List<Note> getAllNotes() {
        return null;
    }

    @Override
    public void addTagsToNote(long id, Set<String> tagValues) {
        User user = userRepository.getById(1L);
        Note note = noteRepository.getById(id);

        for (String tagValue : tagValues) {
            tagService.addTag(tagValue);
            note.addTags(tagRepository.findByUserAndTypeAndValue(user, "custom", tagValue));
        }

        noteRepository.save(note);
    }
}
