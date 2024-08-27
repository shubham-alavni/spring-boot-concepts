package com.lead.cruddemo;

import com.lead.cruddemo.dao.AppDAO;
import com.lead.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(CruddemoApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(AppDAO appDAO) {
    return args -> {
      // createInstructor(appDAO);
      // findInstructor(appDAO);
      // deleteInstructor(appDAO);
      // findInstructorDetail(appDAO);
      // deleteInstructorDetail(appDAO);
      // createInstructorWithCourses(appDAO);
      // findInstructorWithCourses(appDAO);
      // findCoursesForInstructor(appDAO);
      // findInstructorWithCoursesJoinFetch(appDAO);
      // updateInstructor(appDAO);
      // updateCourse(appDAO);
      // deleteInstructor(appDAO);
      // deleteCourseById(appDAO);
      // createCourseAndReviews(appDAO);
      // retrieveCourseAndReviews(appDAO);
      // deleteCourseById(appDAO);
      // createCourseAndStudents(appDAO);
      // findCourseAndStudents(appDAO);
      // findStudentAndCourses(appDAO);
      // addMoreCoursesForStudent(appDAO);
      // deleteCourseById(appDAO);
      deleteStuduent(appDAO);
    };
  }

  private void deleteStuduent(AppDAO appDAO) {
    int studentId = 2;
    System.out.println("Deleting student with id: " + studentId);
    appDAO.deleteStudentById(studentId);
    System.out.println("Done!");
  }

  private void addMoreCoursesForStudent(AppDAO appDAO) {
    int studentId = 2;

    Student student = appDAO.findStudentAndCoursesById(studentId);

    Course course1 = new Course("Rubik's Cube - How to Speed Cube");
    Course course2 = new Course("Atari 2600 - Game Development");

    student.addCourse(course1);
    student.addCourse(course2);

    appDAO.update(student);
  }

  private void findStudentAndCourses(AppDAO appDAO) {
    int theId = 1;
    Student student = appDAO.findStudentAndCoursesById(theId);
    System.out.println("Student: " + student);
    System.out.println("Courses: " + student.getCourses());
    System.out.println("Done!");
  }

  private void findCourseAndStudents(AppDAO appDAO) {
    int theId = 11;
    Course course = appDAO.findCourseAndStudentsById(theId);
    System.out.println("Course: " + course);
    System.out.println("Students: " + course.getStudents());
    System.out.println("Done!");
  }

  private void createCourseAndStudents(AppDAO appDAO) {
    // Create a course
    Course course = new Course("Pacman - How To Score One Million Points");

    // Create some students
    Student student1 = new Student("John", "Doe", "john.doe@luv2code.com");
    Student student2 = new Student("Mary", "Public", "mary.public@luv2code.com");

    // Add students to the course
    course.addStudent(student1);
    course.addStudent(student2);

    // Save the course ... and leverage the cascade all :-)
    System.out.println("Saving the course ..." + course);
    System.out.println("Students: " + course.getStudents());
    appDAO.saveCourse(course);
    System.out.println("Done!");

  }

  private void retrieveCourseAndReviews(AppDAO appDAO) {
    int theId = 11;
    Course course = appDAO.findCourseAndReviewsById(theId);
    System.out.println("Course: " + course);
    System.out.println("Reviews: " + course.getReviews());
    System.out.println("Done!");
  }

  private void createCourseAndReviews(AppDAO appDAO) {
    // Create a course
    Course course = new Course("Pacman - How To Score One Million Points");

    // Add some reviews
    course.addReview(new Review("Great course ... loved it!"));
    course.addReview(new Review("Cool course, job well done"));
    course.addReview(new Review("What a dumb course, you are an idiot!"));

    // Print out the course and reviews
    System.out.println("Saving the course");
    System.out.println(course);
    System.out.println(course.getReviews());

    // Save the course ... and leverage the cascade all :-)
    appDAO.saveCourse(course);
  }

  private void deleteCourseById(AppDAO appDAO) {
    // delete the course
    int theId = 11;
    System.out.println("Deleting course with id: " + theId);
    appDAO.deleteCourseById(theId);
    System.out.println("Done!");
  }

  private void updateCourse(AppDAO appDAO) {
    // get course by primary key / id
    int theId = 10;
    Course course = appDAO.findCourseById(theId);

    // update the course
    course.setTitle("Pacman - How To Score One Million Points");

    appDAO.updateCourse(course);

    System.out.println("Done!");
  }

  private void updateInstructor(AppDAO appDAO) {
    // get instructor by primary key / id
    int theId = 1;
    Instructor instructor = appDAO.findInstructorById(theId);

    // update the instructor
    instructor.setLastName("TESTER");

    appDAO.update(instructor);

    System.out.println("Done!");
  }

  private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
    int theId = 1;

    System.out.println("Finding instructor with courses using JOIN FETCH: " + theId);
    Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);
    System.out.println("Found instructor: " + instructor);

    System.out.println("the associated courses: " + instructor.getCourses());
    System.out.println("Done!");
  }

  private void findCoursesForInstructor(AppDAO appDAO) {
    // get the instructor by primary key / id
    int theId = 1;
    Instructor instructor = appDAO.findInstructorById(theId);
    System.out.println("Found instructor: " + instructor);

    // get courses for the instructor
    System.out.println("Finding courses for instructor: " + theId);

    List<Course> courses = appDAO.findCoursesByIstructorId(theId);

    // Associate the instructor with the courses
    instructor.setCourses(courses);

    System.out.println("Courses: " + courses);
  }

  private void findInstructorWithCourses(AppDAO appDAO) {
    int theId = 1;
    System.out.println("Finding instructor with courses: " + theId);
    Instructor instructor = appDAO.findInstructorById(theId);
    System.out.println(instructor.getCourses());
  }

  private void createInstructorWithCourses(AppDAO appDAO) {
    // create instructor
     Instructor instructor = new Instructor("Susan", "Public", "susan.public@luv2code.com");

    // create instructor detail
     InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com", "Video Gaming");

    // associate the objects
     instructor.setInstructorDetail(instructorDetail);

    // create some courses
    Course course1 = new Course("Pacman - How To Score One Million Points");
    Course course2 = new Course("Rubik's Cube - Speed Cubing Methods");

    // add courses to instructor
    instructor.add(course1);
    instructor.add(course2);

    // save the instructor
    System.out.println("Saving instructor: " + instructor);
    System.out.println("Saving courses: " + instructor.getCourses());
    appDAO.save(instructor);

    System.out.println("Done!");
  }

  private void deleteInstructorDetail(AppDAO appDAO) {
    // delete the instructor detail
    int theId = 4;
    appDAO.deleteInstructorDetailById(theId);
  }

  private void findInstructorDetail(AppDAO appDAO) {
    // get the instructor detail by primary key / id
    int theId = 2;
    InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theId);

    System.out.println("Found instructor detail: " + instructorDetail);
    System.out.println("the associated instructor: " + instructorDetail.getInstructor());
  }

  private void deleteInstructor(AppDAO appDAO) {
    // delete the instructor
    int theId = 1;
    appDAO.deleteInstructorById(theId);
    System.out.println("Deleted instructor with id: " + theId);
  }

  private void findInstructor(AppDAO appDAO) {
    // get the instructor by primary key / id
    int theId = 1;
    Instructor instructor = appDAO.findInstructorById(theId);

    System.out.println("Found instructor: " + instructor);
    System.out.println("the associated instructor detail: " + instructor.getInstructorDetail());
  }

  private void createInstructor(AppDAO appDAO) {
    // create instructor
    // Instructor instructor = new Instructor("Chad", "Darby", "chad.darby@luv2code.com");

    // create instructor detail
    // InstructorDetail instructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!!!");

    // associate the objects
    // instructor.setInstructorDetail(instructorDetail);
    Instructor instructor = new Instructor("Madhu", "Patel", "madhu.patel@luv2code.com");
    InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com", "Guitar");
    instructor.setInstructorDetail(instructorDetail);
    // save the instructor
    //
    // NOTE: this will also save the details object
    // because of CascadeType.ALL
    //
    System.out.println("Saving instructor: " + instructor);
    appDAO.save(instructor);

    System.out.println("Done!");
  }
}
