package com.learnhibernatejpa.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learnhibernatejpa.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {

  // Define fields for entity manager
  private EntityManager entityManager;

  // Inject entity manager using constructor injection
  @Autowired
  public StudentDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  // implement save method
  @Override
  @Transactional
  public void save(Student theStudent) {
    // save or update the student
    entityManager.persist(theStudent);
  }

  @Override
  public Student findById(Integer id) {
    // get student
    return entityManager.find(Student.class, id);
  }

  @Override
  public List<Student> findAll() {
    // create a query to get all students
    // By default, ORDER BY is ASC, not necessary to write ASC
    TypedQuery<Student> query = entityManager.createQuery("FROM Student ORDER BY lastName ASC", Student.class);

    // This is another way to write the query
    // return entityManager.CreateQuery("FROM Student",
    // Student.class).getResultList();

    return query.getResultList();
  }

  @Override
  public List<Student> findByLastName(String lastName) {
    // create a query to get all students with the given last name
    TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE lastName=:lastName", Student.class);

    // set parameter
    query.setParameter("lastName", lastName);

    // return enetityManager.createQuery("FROM Student WHERE lastName=:lastName",
    // Student.class).setParameter("lastName", lastName).getResultList();

    return query.getResultList();
  }

  @Override
  @Transactional
  public void update(Student theStudent) {
    // update the student
    entityManager.merge(theStudent);
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    // delete the student
    // entityManager.remove(entityManager.find(Student.class, id));

    Student student = entityManager.find(Student.class, id);
    entityManager.remove(student);
  }

  @Override
  @Transactional
  public int deleteAll() {
    // delete all students, returns the number of rows affected
    return entityManager.createQuery("DELETE FROM Student").executeUpdate();
  }

}
