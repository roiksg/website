package com.example.website.controller;

import com.example.website.model.Role;
import com.example.website.model.Story;
import com.example.website.model.User;
import com.example.website.repository.StoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class CreateStoryController {
    @Autowired
    private StoryRepo storyRepo;
    private LocalDateTime date;

    @Value("${upload.path}")
    private String uplodaPath;

    @GetMapping("/createstory")
    public String createstory(Model model){
        Iterable<Story> story = storyRepo.findAll();
        model.addAttribute("story", story);
        model.addAttribute("message", "");
        return "createstory";
    }

    @PostMapping("/createstory")
    public String addStory(@AuthenticationPrincipal User user,
                           @RequestParam (required = false, defaultValue = "") String filter,
                           @RequestParam("cover") MultipartFile cover,
                            @RequestParam String title,
                           @RequestParam String description, @RequestParam String genre,
                           Model model) throws IOException {

        Iterable<Story> stories = storyRepo.findByTitle(title);

        if (stories == null && !title.isEmpty()) {
            model.addAttribute("message", "Story exists!");
            return "createstory";
        }

        date = LocalDateTime.now();
        Story story = new Story(title, description, genre, date, date, user);

        if (cover !=null && !cover.getOriginalFilename().isEmpty()) {
            File uploadFolder = new File(uplodaPath);

            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resulFileName = uuidFile + "." + cover.getOriginalFilename();
            cover.transferTo(new File(uplodaPath +"/"+ resulFileName));
            story.setCover(resulFileName);
        }

        storyRepo.save(story);

        stories = storyRepo.findAll();
        model.addAttribute("story", stories);
        model.addAttribute("message", "");
        return "createstory";
    }

    @PostMapping("filterstory")
    public String filter (@RequestParam String filter, Model model){
        Iterable<Story> story;
        if (filter != null && !filter.isEmpty()) {
            story = storyRepo.findByTitle(filter);
        }
        else {
            story = storyRepo.findAll();
        }
        model.addAttribute("story", story);
        return "createstory";
    }

}
