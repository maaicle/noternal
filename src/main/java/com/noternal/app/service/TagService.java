package com.noternal.app.service;

import com.noternal.app.entity.Tag;
import com.noternal.app.entity.User;

import java.util.List;

public interface TagService {
    void addTag(String value);
    List<Tag> getAllTags();
}
