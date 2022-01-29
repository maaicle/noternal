package com.noternal.app.service;

import com.noternal.app.entity.Tag;

import java.util.Optional;
import java.util.Set;

public interface TagService {
    Optional<Tag> addTag(String value);
    Set<Tag> getAllTags();
}
