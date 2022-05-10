package com.ledelsea.training.service;

import java.util.List;

import com.ledelsea.training.dao.StudentDao;
import com.ledelsea.training.data.Student;

public class StudentService {
	private StudentDao studentDao = null;

	public StudentService() {
		this.studentDao = new StudentDao();
	}
	
	//log4j
	public static void main(String[] args) {

		StudentService service = new StudentService();
		Student student = new Student();
		student.setFirstName("Ravz");
		student.setLastName("B");
		student.setAddress("123 First St");
		student.setCity("Edision");
		student.setState("NJ");
		
		service.create(student);
		service.findAll();
	}

	public void create(Student student) {

		boolean status = studentDao.create(student);
		if (status) {
			System.out.println("Student created successfully.");
		}
	}

	public List<Student> findAll() {
		System.out.println("Dao...findAll");
		List<Student> students = studentDao.findAll();
		for (Student student : students) {
			System.out.println(student);
		}
		return students;
	}

}
