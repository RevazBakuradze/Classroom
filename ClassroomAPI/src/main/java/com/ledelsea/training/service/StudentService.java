package com.ledelsea.training.service;

import java.util.List;
import java.util.Scanner;

import com.ledelsea.training.dao.StudentDao;
import com.ledelsea.training.data.Student;

public class StudentService {
	private StudentDao studentDao = null;

	public StudentService() {
		this.studentDao = new StudentDao();
	}
	
	//log4j
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Classroom application");

		displayOptions();

		boolean switchIsOn = true;

		while (switchIsOn) {
			int option = scanner.nextInt();

			switch (option){
				case 0:
					System.out.println('0');
					break;
				case 1:
					addNewStudent(scanner);
					break;
				case 2:
					System.out.println('2');
					break;
				case 3:
					System.out.println("Quitting");
					scanner.close();
					switchIsOn = false;
					break;
				default:
					System.out.println(option + " is not an option");
					displayOptions();
					break;
			}
		}


//		service.findAll();

	}

	public static void displayOptions() {
		System.out.println(
				"\nPlease select the option:" +
				"\n\t 0 - See all students" +
				"\n\t 1 - Add a new student" +
				"\n\t 2 - Edit the existing student" +
				"\n\t 3 - Quit");
	}

	public static void addNewStudent(Scanner scanner) {

		StudentService service = new StudentService();
		Student student = new Student();

		scanner.nextLine();
		System.out.println("Please enter the student's first name:");
		String firstName = scanner.nextLine();
		student.setFirstName(firstName);
		System.out.println("Please enter the student's last name:");
		String lastName = scanner.nextLine();
		student.setLastName(lastName);
		System.out.println("Please enter the student's address:");
		String address = scanner.nextLine();
		student.setAddress(address);
		System.out.println("Please enter the student's city:");
		String city = scanner.nextLine();
		student.setCity(city);
		System.out.println("Please enter the student's state:");
		String state = scanner.nextLine();
		student.setState(state);

		System.out.println(student);

		service.create(student);
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
