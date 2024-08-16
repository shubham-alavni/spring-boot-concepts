package com.lead.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lead.springboot.thymeleafdemo.model.Student;

@Controller
public class StudentController {

  @Value("${countries}")
  private List<String> countries;

  @Value("${app.favorite.programming.languages}")
  private List<String> favoriteLanguages;

  @Value("${app.favorite.operating.systems}")
  private List<String> operatingSystems;

  @GetMapping("/showStudentForm")
  public String showForm(Model theModel) {
    // Create a new Student object
    Student theStudent = new Student();

    // Add student object to the model
    theModel.addAttribute("student", theStudent);

    // Add the countries to the model
    theModel.addAttribute("countries", countries);

    // Add the favorite programming languages to the model
    theModel.addAttribute("favoriteLanguages", favoriteLanguages);

    // Add the operating systems to the model
    theModel.addAttribute("operatingSystems", operatingSystems);

    return "student-form";
  }

  @PostMapping("/processStudentForm")
  public String processStudentForm(@ModelAttribute("student") Student theStudent) {
    // Log the input data
    System.out.println("theStudent: " + theStudent.getFirstName() + " " + theStudent.getLastName());

    // Return a confirmation message

    return "student-confirmation";
  }

}
