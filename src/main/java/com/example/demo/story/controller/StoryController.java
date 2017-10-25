package com.example.demo.story.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.story.dto.Pager;
import com.example.demo.story.dto.Pagination;
import com.example.demo.story.model.Story;
import com.example.demo.story.repository.StoryRepository;
import com.example.demo.story.service.StoryService;

@RestController
@RequestMapping("/api")
public class StoryController {
	@Autowired
	private StoryRepository repo;
	@Autowired
	private StoryService service;
	
	@GetMapping("/stories")
	public Object getStories(
			@RequestParam(name="page", required=false, defaultValue="1") int page,
			@RequestParam(name="size", required=false, defaultValue="10") int size){
		Pagination pagination = service.selectByLimit(page, size);
		return pagination;
	}
	
	// --------------------------------------------------- extension service start
	// This code is for HW of students.
	private final String TARGET_OF_STORY = "/api/storiez";
	
	@GetMapping("/storiez")
	public Object getStoriesForPager(
			@RequestParam(name="page", required=false, defaultValue="1") int page,
			@RequestParam(name="size", required=false, defaultValue="10") int size,
			@RequestParam(name="bsize", required=false, defaultValue="5") int bsize){
		Pager pager = service.selectByRange(page, size, bsize, TARGET_OF_STORY);
		return pager;
	}
	// --------------------------------------------------- extension service end
	
	@GetMapping("/stories/{id}")
	public Object getStoryById(@PathVariable int id){
		Story story = repo.findOne(id);
		return story;
	}
	
	@PostMapping("/stories")
	public Object postStory(@RequestBody Story story){
		Story storyPersisted = repo.save(story);
		return storyPersisted;
	}
	
	@PatchMapping("/stories/{id}")
	public Object patchStoryById(
			@RequestBody Story story,
			@PathVariable int id){
		story.setId(id);
		Story storyUpdated = repo.save(story);
		return storyUpdated;
	}
	
	@DeleteMapping("/stories/{id}")
	public Object deleteStoryById(@PathVariable int id){
		repo.delete(id);
		Map<String, Integer> map = new HashMap<>();
		map.put("id", id);
		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}
}
