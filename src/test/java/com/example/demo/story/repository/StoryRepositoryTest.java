package com.example.demo.story.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.story.model.Story;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class StoryRepositoryTest {
	@Autowired
	StoryRepository repo;
	
	@Test
	public void testFindAll() {
		List<Story> stories = repo.findAll();
		stories.forEach(System.out::println);
	}

}
