package com.example.demo.story.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.story.dto.Pager;
import com.example.demo.story.dto.Pagination;
import com.example.demo.story.model.Story;
import com.example.demo.story.repository.StoryRepository;

@Service
public class StoryService {
	@Autowired
	private StoryRepository repo;
	
	public Pagination selectByLimit(int page, int size){
		// 1-base paging index
		page = page > 0 ? (page - 1) : page;
		
		Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "id");
		Page<Story> result = repo.findAll(pageable);
		List<Story> stories = result.getContent();
		
		page = page + 1;
		Pagination pagination = new Pagination(page, size, result.getTotalElements(), stories);
		return pagination;
	}
	
	public Pager selectByRange(int page, int size, int bsize, String target){
		// 1-base paging index
		page = page > 0 ? (page - 1) : page;
		
		Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "id");
		Page<Story> result = repo.findAll(pageable);
		List<Story> stories = result.getContent();
		
		page = page + 1;
		Pager pager = new Pager(page, size, bsize, result.getTotalElements(), stories, target);
		return pager;
	}
}
