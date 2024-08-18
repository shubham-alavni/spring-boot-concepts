package com.lead.springboot.mvc.validationdemo.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lead.springboot.mvc.validationdemo.model.Customer;

import jakarta.validation.Valid;

@Controller
public class CustomerController {

  // Add an initbinder to convert trim input strings
  // Remove leading and trailing whitespace
  // Resolve issue for our validation
  @InitBinder
  public void initBinder(WebDataBinder dataBinder) {
    StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

    dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
  }

  // Get mapping for /showForm
  @GetMapping("/")
  public String showForm(Model theModel) {
    // Create a new Customer object
    Customer theCustomer = new Customer();

    // Add the customer object to the model
    theModel.addAttribute("customer", theCustomer);

    return "customer-form";
  }

  @PostMapping("/processForm")
  public String processFrom(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {

    // debuging
    System.out.println("Binding result: " + theBindingResult.toString());
    System.out.println("\n\n\n\n");

    System.out.println("Last name: |" + theCustomer.getLastName() + "|");

    if (theBindingResult.hasErrors()) {
      return "customer-form";
    }
    return "customer-confirmation";
  }
}
