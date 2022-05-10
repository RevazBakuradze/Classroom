package com.ledelsea.training.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.ledelsea.training.dao.StudentDao;
import com.ledelsea.training.data.Student;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceMockTest {

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@InjectMocks
	StudentDao studentDao = new StudentDao();
	
	@Mock
	StudentService studentService;
	
	@Test
	public void findAllStudentsTest() {
		List<Student> students = new ArrayList<Student>();
		
		Student student = new Student();
		student.setFirstName("Brahma");
		student.setLastName("Kanaparthi");
		student.setAddress("123 First St");
		student.setCity("Ellicott City");
		student.setState("MD");
		students.add(student);
		
		when(studentService.findAll()).thenReturn(students);
		
		List<Student> expectedStudents = studentService.findAll();
		
		Assert.assertEquals(1, expectedStudents.size());
		Assert.assertEquals("Brahma", expectedStudents.get(0).getFirstName());
	}
}
