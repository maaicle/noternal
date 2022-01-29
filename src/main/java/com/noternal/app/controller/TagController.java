package com.noternal.app.controller;

import com.noternal.app.entity.Tag;
import com.noternal.app.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    public Set<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @PostMapping("/tags")
    public String addTag(@RequestBody Tag tag) {
        tagService.addTag(tag.getValue());
        return String.format("Tag: %s Added Successfully", tag.getValue());
    }
}
