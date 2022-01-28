package com.noternal.app.controller;

import com.noternal.app.entity.Tag;
import com.noternal.app.entity.User;
import com.noternal.app.model.UserDto;
import com.noternal.app.service.TagService;
import com.noternal.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @PostMapping("/tags")
    public String addTag(@RequestBody Tag tag) {
        tagService.addTag(tag.getValue());
        return String.format("Tag: %s Added Successfully", tag.getValue());
    }
}
