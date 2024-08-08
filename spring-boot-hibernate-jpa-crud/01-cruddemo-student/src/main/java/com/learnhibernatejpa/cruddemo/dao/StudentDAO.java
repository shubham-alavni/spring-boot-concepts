package com.learnhibernatejpa.cruddemo.dao;

import java.util.List;

import com.learnhibernatejpa.cruddemo.entity.Student;

public interface StudentDAO {
  void save(Student theStudent);

  Student findById(Integer id);

  // method to find all students
  List<Student> findAll();

  List<Student> findByLastName(String lastName);

  void update(Student theStudent);

  void delete(Integer id);

  int deleteAll();
}
