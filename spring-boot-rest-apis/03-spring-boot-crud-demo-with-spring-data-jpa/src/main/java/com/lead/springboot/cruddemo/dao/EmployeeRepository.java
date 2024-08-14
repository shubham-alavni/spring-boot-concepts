package com.lead.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
