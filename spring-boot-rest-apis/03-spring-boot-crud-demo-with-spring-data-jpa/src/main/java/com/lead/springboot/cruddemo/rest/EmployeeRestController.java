package com.lead.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lead.springboot.cruddemo.entity.Employee;
import com.lead.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

  // quick and dirty: inject employee dao
  private EmployeeService employeeService;

  // set up constructor injection
  public EmployeeRestController(EmployeeService theEmployeeService) {
    employeeService = theEmployeeService;
  }

  // expose "/employees" and return list of employees
  @GetMapping("/employees")
  public List<Employee> findAll() {
    return employeeService.findAll();
  }

  // add mapping for GET /employees/{employeeId}
  @GetMapping("/employees/{employeeId}")
  public Employee getEmployee(@PathVariable int employeeId) {
    System.out.println("In getEmployee");
    Employee theEmployee = employeeService.findById(employeeId);

    if (theEmployee == null) {
      throw new RuntimeException("Employee id not found - " + employeeId);
    }

    return theEmployee;
  }

  // add mapping for POST /employees - add new employee
  @PostMapping("/employees")
  public Employee addEmployee(@RequestBody Employee theEmployee) {

    // also just in case they pass an id in JSON ... set id to 0
    // this is to force a save of new item ... instead of update
    theEmployee.setId(0);

    return employeeService.save(theEmployee);

  }

  // add mapping for PUT /employees - update existing employee)
  @PutMapping("/employees/{employeeId}")
  public Employee updateEmployee(@PathVariable int employeeId, @RequestBody Employee theEmployee) {

    Employee employee = employeeService.findById(employeeId);

    if (employee == null) {
      throw new RuntimeException("Employee id not found - " + employeeId);
    }

    theEmployee.setId(employeeId);

    return employeeService.save(theEmployee);
  }

  // add mapping for DELETE /employees/{employeeId} - delete employee
  @DeleteMapping("/employees/{employeeId}")
  public String deleteEmployee(@PathVariable int employeeId) {
    Employee employee = employeeService.findById(employeeId);

    if (employee == null) {
      throw new RuntimeException("Employee id not found - " + employeeId);
    }

    employeeService.deleteById(employeeId);

    return "Deleted employee id - " + employeeId;
  }
}