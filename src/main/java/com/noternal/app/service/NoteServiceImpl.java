package com.noternal.app.service;

import com.noternal.app.entity.Note;
import com.noternal.app.entity.Tag;
import com.noternal.app.entity.User;
import com.noternal.app.repository.NoteRepository;
import com.noternal.app.repository.TagRepository;
import com.noternal.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private NoteEventService noteEventService;

    @Autowired
    private TagService tagService;

    @Override
    public void addNote(String body, Set<String> tagValues) {
        User user = userRepository.getById(1L);
        Set<Tag> userTag = tagRepository.findByUserAndTypeAndValue(user, "user", user.getUsername());
        Note note = new Note(body, userTag);

        for (String tagValue : tagValues) {
            tagService.addTag(tagValue);
            note.addTags(tagRepository.findByUserAndTypeAndValue(user, "custom", tagValue));
        }

        noteRepository.save(note);
        noteEventService.addNoteEvent(note);
    }

    @Override
    public List<Note> getAllNotes() {
        return null;
    }

    @Override
    public void addTagsToNote(long noteId, Set<String> tagValues) {
        Note note = noteRepository.getById(noteId);
        User user = userRepository.getById(1L);

        for (String tagValue : tagValues) {
            tagService.addTag(tagValue);
            note.addTags(tagRepository.findByUserAndTypeAndValue(user, "custom", tagValue));
        }

        noteRepository.save(note);
    }

    @Override
    public void removeTagsFromNote(long noteId, Set<String> tagValues) {
        Note note = noteRepository.getById(noteId);
        User user = userRepository.getById(1L);

        for (String tagValue : tagValues) {
            Set<Tag> tags = tagRepository.findByUserAndTypeAndValue(user, "custom", tagValue);
            note.removeTags(tags);
        }

        noteRepository.save(note);

    }

    @Override
    public void updateNote(long noteId, String body, Set<String> tagValues, boolean archived) {
        Note note = noteRepository.getById(noteId);
        User user = userRepository.getById(1L);
        Set<Tag> currentTags = tagRepository.findByUserAndNotesAndType(user, note, "custom");
        note.removeTags(currentTags);

        for (String tagValue : tagValues) {
            tagService.addTag(tagValue);
            note.addTags(tagRepository.findByUserAndTypeAndValue(user, "custom", tagValue));
        }

        note.updateNote(noteId, body, archived);
        noteRepository.save(note);
        noteEventService.addNoteEvent(note);

    }

//    @Override
//    public User getUserFromTag(Note note) {
//        Set<Tag> tagList = note.getTags();
//        Optional<User> user = Optional.empty();
//        for (Tag tag : tagList) {
//            if (tag.getType().equals("username") && tag.getValue().equals("username")) {
//                user = Optional.of(tag.getUser());
//
//            }
//        }
//        System.out.println("NoteServiceImpl | getUserFromTag " + user);
//        return user.get();
//    }
}
