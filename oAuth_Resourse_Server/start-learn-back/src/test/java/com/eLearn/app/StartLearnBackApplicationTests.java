package com.eLearn.app;

import com.eLearn.app.Repositories.CategoryRepo;
import com.eLearn.app.Repositories.CourseRepo;
import com.eLearn.app.Repositories.VideoRepo;
import com.eLearn.app.services.CategoryService;
import com.eLearn.app.services.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class StartLearnBackApplicationTests {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private VideoService videoService;
	@Autowired
	private VideoRepo videoRepo;
	@Test
	void contextLoads() {
	}
	@Test
	public void testCategoryCourseRelation(){
		categoryService.addCourseToCategory("3044f37f-b946-4f29-81b4-e7984e3f1416","d244538e-754e-484b-bf66-e97a0abb6809");
	}

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private CourseRepo courseRepo;
	@Test
	@Transactional
	public void testRelation(){
		int size = categoryRepo.findById("3044f37f-b946-4f29-81b4-e7984e3f1416").get().getCourseList().size();
		System.out.println("Size of Course : " + size);

		int size1 = courseRepo.findById("229a9f2c-e975-4ef5-95ea-62d62e25dd74").get().getCategoryList().size();
		System.out.println("Size of Category : "+size1);
	}

	@Test
	@Transactional
	public void testVideoCourseRelation(){
		videoService.addVideoToCourse("34a1594c-d110-4595-8793-e24fd6ec9c51","c493b5c9-1eab-4527-9980-453fe84d0004");
	}

}
