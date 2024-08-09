package com.learn_rest_api.rest_demo.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoRestController {

  // add code for the "/hello" endpoint
  @RequestMapping("/hello")
  public String sayHello() {
    return "Hello World!";
  }

}
