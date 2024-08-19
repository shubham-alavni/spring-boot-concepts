package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  // create a mapping for "/list"
  @GetMapping("/list")
  public String listEmployees(Model model) {
    model.addAttribute("employees", employeeService.findAll());
    return "employees/list-employees";
  }

  // Add another mapping for "/showFormForAdd"
  @GetMapping("/showFormForAdd")
  public String showFormForAdd(Model model) {
    // Create model attribute to bind form data
    model.addAttribute("employee", new Employee());
    return "employees/employee-form";
  }

  // save the employee
  @PostMapping("/save")
  public String saveEmployee(@ModelAttribute("employee") Employee employee) {
    // save the employee
    employeeService.save(employee);
    // use a redirect to prevent duplicate submissions
    return "redirect:/employees/list";
  }

  // Add another mapping for "/showFormForUpdate"
  @GetMapping("/showFormForUpdate")
  public String showFormForUpdate(@RequestParam("employeeId") int theId, Model model) {
    // get the employee from the service
    Employee theEmployee = employeeService.findById(theId);

    // set employee as a model attribute to pre-populate the form
    model.addAttribute("employee", theEmployee);

    // send over to our form
    return "employees/employee-form";
  }

  // Add another mapping for "/delete"
  @GetMapping("/delete")
  public String delete(@RequestParam("employeeId") int theId) {
    // delete the employee
    employeeService.deleteById(theId);

    // redirect to /employees/list
    return "redirect:/employees/list";
  }
}
