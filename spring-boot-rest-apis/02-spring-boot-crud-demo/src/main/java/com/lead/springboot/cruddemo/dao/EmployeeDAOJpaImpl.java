package com.lead.springboot.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lead.springboot.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

  // define field for entity manager
  private EntityManager entityManager;

  // set up constructor injection
  @Autowired
  public EmployeeDAOJpaImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Employee> findAll() {
    // create a query
    TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee",
        Employee.class);

    // execute query and get result list
    List<Employee> employees = theQuery.getResultList();

    // Shorter version of the above code
    // return entityManager.createQuery("from Employee",
    // Employee.class).getResultList();

    // return the results
    return employees;
  }

  @Override
  public Employee findById(int theId) {
    // get employee
    Employee theEmployee = entityManager.find(Employee.class, theId);

    // return employee
    return theEmployee;
  }

  @Override
  public Employee save(Employee theEmployee) {
    // save or update the employee
    Employee dbEmployee = entityManager.merge(theEmployee);

    // return the employee
    return dbEmployee;
  }

  @Override
  public void deleteById(int theId) {
    // find the employee
    Employee theEmployee = entityManager.find(Employee.class, theId);

    // delete the employee
    entityManager.remove(theEmployee);
  }

}
