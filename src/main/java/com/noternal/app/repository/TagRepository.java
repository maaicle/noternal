package com.noternal.app.repository;

import com.noternal.app.entity.Note;
import com.noternal.app.entity.Tag;
import com.noternal.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Set<Tag> findByUserAndTypeAndValue(User user, String type, String value);
    Set<Tag> findByUser(User user);
    boolean existsTagByUserAndTypeAndValue(User user, String type, String Value);
    Set<Tag> findByUserAndNotesAndType(User user, Note note, String type);
}
