package com.example.website.repository;

import com.example.website.model.Story;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoryRepo extends CrudRepository<Story, Integer> {
    List<Story> findByTitle(String title);
}
