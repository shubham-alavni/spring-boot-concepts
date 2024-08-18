package com.lead.springboot.mvc.validationdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

  private String coursePrefix;

  @Override
  public void initialize(CourseCode theCourseCode) {
    coursePrefix = theCourseCode.value();
  }

  @Override
  public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {

    return theCode != null ? theCode.startsWith(coursePrefix) : true;
  }

}
