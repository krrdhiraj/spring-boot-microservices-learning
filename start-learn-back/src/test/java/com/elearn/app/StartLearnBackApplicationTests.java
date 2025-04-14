package com.elearn.app;

import com.elearn.app.config.securities.JwtUtil;
import com.elearn.app.repositories.CategoryRepo;
import com.elearn.app.repositories.CourseRepo;
import com.elearn.app.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.synth.SynthUI;

@SpringBootTest
class StartLearnBackApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private CourseRepo courseRepo;

	@Test
	public void testCategoryCourseRelation(){
		categoryService.addCourseToCategory("a6759088-a9aa-4d3f-8bf5-eb67b53b7102","300cfa10-d436-415f-83f2-2e237452a8ee");
	}

	@Test
	@Transactional
	public void testCountCategorySize(){
		int size = categoryRepo.findById("300cfa10-d436-415f-83f2-2e237452a8ee").get().getCourseList().size();
		System.out.println(size);
		int size1 = courseRepo.findById("a6759088-a9aa-4d3f-8bf5-eb67b53b7102").get().getCategoryList().size();
		System.out.println(size1);
	}

	@Autowired
	private JwtUtil jwtUtil;

	@Test
	public void testJwt(){
		System.out.println("Testing jwt.");

		String token = jwtUtil.generateToken("Dhiraj Kumar");
		System.out.println(token);

		System.out.println(jwtUtil.validateToken(token, "Dhiraj Kumar"));

		System.out.println(jwtUtil.extractUsername(token));
	}

}
