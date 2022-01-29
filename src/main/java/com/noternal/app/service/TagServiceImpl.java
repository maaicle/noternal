package com.noternal.app.service;

import com.noternal.app.entity.Tag;
import com.noternal.app.entity.User;
import com.noternal.app.repository.TagRepository;
import com.noternal.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<Tag> addTag(String value) {
        User user = userRepository.getById(1L);
        if (!tagRepository.existsTagByUserAndTypeAndValue(user, "custom", value)) {
            Tag tag = new Tag(user, "custom", "custom", value);
            tagRepository.save(tag);
            return Optional.of(tag);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Set<Tag> getAllTags() {
        return null;
    }
}
