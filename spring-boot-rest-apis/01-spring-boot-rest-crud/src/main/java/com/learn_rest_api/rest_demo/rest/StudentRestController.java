package com.learn_rest_api.rest_demo.rest;

import java.util.ArrayList;
import java.util.List;

import com.learn_rest_api.rest_demo.entity.Student;

import jakarta.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentRestController {

  // define List of students
  private List<Student> theStudents;

  // @PostConstruct to load the student data only once, when the class is loaded
  @PostConstruct
  public void loadData() {
    theStudents = new ArrayList<>();

    theStudents.add(new Student("John", "Doe"));
    theStudents.add(new Student("Mary", "Rossi"));
    theStudents.add(new Student("Tom", "Smith"));
  }

  // define endpoint for "/students" - return list of students
  @GetMapping("/students")
  public List<Student> getStudents() {
    return theStudents;
  }

  // define endpoint for "/students/{studentId}" - return student at index
  @GetMapping("students/{studentId}")
  public Student getMethodName(@PathVariable int studentId) {

    if (studentId >= theStudents.size() || (studentId < 0)) {
      throw new StudentNotFoundException("Student id not found - " + studentId);
    }

    return theStudents.get(studentId);
  }

}
