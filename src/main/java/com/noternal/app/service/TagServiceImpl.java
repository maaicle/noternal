package com.noternal.app.service;

import com.noternal.app.entity.Tag;
import com.noternal.app.entity.User;
import com.noternal.app.repository.TagRepository;
import com.noternal.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void addTag(String value) {
        User user = userRepository.getById(1L);
        Tag tag = new Tag(user, "custom", "custom", value);
        tagRepository.save(tag);
    }

    @Override
    public List<Tag> getAllTags() {
        return null;
    }
}
