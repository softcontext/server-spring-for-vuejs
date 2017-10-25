package com.example.demo.story.dto;

import java.util.List;

import com.example.demo.story.model.Story;

import lombok.Data;

@Data
public class Pagination {
	private long total;
	private int per_page;
	private int current_page;
	private int last_page;
	private String next_page_url;
	private String prev_page_url;
	private int from;
	private int to;
	private List<Story> data;
	
	public Pagination(int page, int size, long rows, List<Story> stories){
		total = rows;
		per_page = size;
		current_page = page;

		last_page = (int) Math.ceil((double) total / per_page);
		
		next_page_url = "";
		if (current_page < last_page) {
			next_page_url = "/api/stories?page="+(current_page+1);
		}
		
		prev_page_url = "";
		if (1 < current_page) {
			prev_page_url = "/api/stories?page="+(current_page-1);
		}
		
		/*
		 * page		size	from	to
		 * 1		10		1		10
		 * 2		10		11		20
		 * 3		10		21		30
		 * ...
		 */
		
		to = current_page * per_page;
		from = to - per_page + 1;
		
		data = stories;
	}
}
