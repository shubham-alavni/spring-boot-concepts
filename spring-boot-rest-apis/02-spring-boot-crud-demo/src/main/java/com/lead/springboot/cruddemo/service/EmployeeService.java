package com.lead.springboot.cruddemo.service;

import java.util.List;

import com.lead.springboot.cruddemo.entity.Employee;

public interface EmployeeService {
  public List<Employee> findAll();
}
