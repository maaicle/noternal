package com.noternal.app.repository;

import com.noternal.app.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteEventRepository extends JpaRepository<Tag, Long> {
}
