package com.lead.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.springboot.cruddemo.dao.EmployeeRepository;
import com.lead.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  // constructor injection
  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee findById(int theId) {
    Optional<Employee> result = employeeRepository.findById(theId);

    Employee theEmployee = null;

    if (result.isPresent()) {
      theEmployee = result.get();
    } else {
      // we didn't find the employee
      throw new RuntimeException("Did not find employee id - " + theId);
    }
    // alternatively, we can return null
    // return employeeRepository.findById(theId).orElse(null);
    return theEmployee;
  }

  @Override
  public Employee save(Employee theEmployee) {
    return employeeRepository.save(theEmployee);
  }

  @Override
  public void deleteById(int theId) {
    employeeRepository.deleteById(theId);
  }

}
