package com.noternal.app.repository;

import com.noternal.app.entity.Note;
import com.noternal.app.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByOrderByIdAsc();
    List<Note> findByArchivedOrderByIdAsc(boolean archived);
    List<Note> findByArchivedAndTagsInOrderByIdAsc(boolean archived, Set<Tag> tags);
}
