package com.lead.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lead.springboot.cruddemo.entity.Employee;

@RepositoryRestResource(path = "members") // this is the path to the API
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
