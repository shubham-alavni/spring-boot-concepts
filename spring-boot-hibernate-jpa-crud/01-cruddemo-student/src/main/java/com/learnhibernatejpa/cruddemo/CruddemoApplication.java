package com.learnhibernatejpa.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.learnhibernatejpa.cruddemo.dao.StudentDAO;
import com.learnhibernatejpa.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// createStudent(studentDAO);
			createMultipleStudents(studentDAO);
			// readStudent(studentDAO);
			// queryForStudents(studentDAO);
			// queryForStudentsByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);
			// deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		// delete all students
		System.out.println("Deleting all students...");
		int deletedStudents = studentDAO.deleteAll();
		System.out.println("Deleted students: " + deletedStudents);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		// delete student by id
		System.out.println("Deleting student with id 3...");
		studentDAO.delete(3);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// get student by id
		Student student = studentDAO.findById(1);

		// set student firstName to "Scooby"
		student.setFirstName("Scooby");

		// update the student
		studentDAO.update(student);

		// print the updated student
		System.out.println("Updated student: " + student);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get students by last name
		// studentDAO.findByLastName("Doe").forEach(System.out::println);
		List<Student> students = studentDAO.findByLastName("Doe");

		// display students
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get all students
		System.out.println("Getting all students...");
		studentDAO.findAll().forEach(System.out::println);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// Create multiple students
		System.out.println("Creating multiple student objects...");
		Student student1 = new Student("John", "Doe", "john.doe@mailinator.com");
		Student student2 = new Student("Mary", "Public", "mary@mailinator.com");
		Student student3 = new Student("Bon", "Applebum", "bon@mailinator.com");

		// Save the students
		System.out.println("Saving the students...");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);
	}

	private void createStudent(StudentDAO studentDAO) {
		// Create a student
		System.out.println("Creating new student object...");
		Student student = new Student("Paul", "Wall", "paul.wall@mainlinator.com");

		// Save the student object
		System.out.println("Saving the student...");
		studentDAO.save(student);

		// display id of the saved student
		System.out.println("Saved student: " + student.getId());
	}

	private void readStudent(StudentDAO studentDAO) {
		// Create a student object
		System.out.println("Creating new student object...");
		Student student = new Student("james", "pearl", "james@mailinator.com");
		System.out.println("Student Id before saving: " + student.getId());

		// Save the student object
		System.out.println("Saving the student...");
		studentDAO.save(student);

		// display id of the saved student
		int theId = student.getId();
		System.out.println("Saved student: Generated id: " + theId);

		// retrieve student from database using id
		studentDAO.findById(theId);

		// display student
		System.out.println("Retrieved student: " + student);
	}

}
