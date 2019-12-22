package com.example.website.controller;

import com.example.website.model.Role;
import com.example.website.model.Story;
import com.example.website.model.User;
import com.example.website.repository.StoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CreateStoryController {
    @Autowired
    private StoryRepo storyRepo;
    private LocalDateTime date;

    @GetMapping("/createstory")
    public String createstory(){
        return "createstory";
    }

    @PostMapping("/createstory")
    public String addStory(@AuthenticationPrincipal User user,
                            @RequestParam String title, @RequestParam String cover,
                           @RequestParam String description, @RequestParam String genre,
                           Map<String, Object> model){
        Iterable<Story> stories = storyRepo.findByTitle(title);
        if (stories == null && !title.isEmpty()) {
            model.put("message", "Story exists!");
            return "createstory";
        }
        date = LocalDateTime.now();
        Story story = new Story(title, cover, description, genre, date, date, user);
        storyRepo.save(story);

        return "redirect:/main";
    }

    @PostMapping("filterstory")
    public String filter (@RequestParam String filter, Map<String, Object> model){
        Iterable<Story> story;
        if (filter != null && !filter.isEmpty()) {
            story = storyRepo.findByTitle(filter);
        }
        else {
            story = storyRepo.findAll();
        }
        model.put("story", story);
        return "createstory";
    }

}
