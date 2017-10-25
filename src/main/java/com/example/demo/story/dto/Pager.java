package com.example.demo.story.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.story.model.Story;

import lombok.Data;

/*
 * 페이징 링크를 제공하기위한 DTO
 */
@Data
public class Pager {
	private int page; // current page
	private int size; // rows per page
	private int bsize; // pages per block

	private long rows; // total elements
	private int pages; // total pages
	private int blocks; // total blocks

	private int block; // current block
	private int bspage; // current block start page
	private int bepage; // current block end page
	
	private List<Story> data;

	public Pager(int page, int size, int bsize, long rows, List<Story> stories, String target) {
		this.page = page;
		this.size = size;
		this.bsize = bsize;
		this.rows = rows;
		this.data = stories;

		pages = (int) Math.ceil((double) this.rows / this.size);
		blocks = (int) Math.ceil((double) pages / this.bsize);

		block = (int) Math.ceil((double) this.page / this.bsize);
		bepage = block * this.bsize;
		bspage = bepage - this.bsize + 1;
		
		/*
		 * 페이지 링크 제공 서비스
		 * 위에서 제공하는 값으로 클라이언트 측에서 직접 필요한 값을 계산하는 대신 서버에서 계산해서 데이터와 함께 링크정보를 제공한다.
		 */
		
		isPrevBlock = bspage > bsize ? true : false;
		isNextBlock = bepage < pages ? true : false;
		
		prevBlockUrl = "";
		if (isPrevBlock) {
			prevBlockUrl = target + "?page="+(bspage-1)+"&size="+size+"&bsize="+bsize;
		}
		
		nextBlockUrl = "";
		if (isNextBlock) {
			nextBlockUrl = target + "?page="+(bepage+1)+"&size="+size+"&bsize="+bsize;
		}
		
		firstBlockUrl = target + "?page=1&size="+size+"&bsize="+bsize;
		lastBlockUrl = target + "?page="+pages+"&size="+size+"&bsize="+bsize;
		
		pageUrls = new ArrayList<>();
		for (int pno = bspage; pno <= bepage; pno++) {
			if (pno == page) {
				pageUrls.add("<li class=\"active\">"+pno+"</li>");
			} else {
				if (pno <= pages) {
					pageUrls.add("<li><a href=\""+target + "?page="+pno+"&size="+size+"&bsize="+bsize+"\">"+pno+"</a></li>");
				}
			}
		}
	}
	
	private boolean isPrevBlock;
	private boolean isNextBlock;
	
	private String prevBlockUrl;
	private String nextBlockUrl;
	
	private String firstBlockUrl;
	private String lastBlockUrl;
	
	private List<String> pageUrls;
}
