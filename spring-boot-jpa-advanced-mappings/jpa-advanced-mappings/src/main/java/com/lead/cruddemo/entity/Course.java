package com.lead.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

  // Define fields

  // Define constructors

  // Define getter/setter

  // Define toString

  // Annotate fields

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "title")
  private String title;

  // Default fetch type is FetchType is EAGER
  @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
  @JoinColumn(name = "instructor_id")
  private Instructor instructor;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "course_id")
  private List<Review> reviews;

  @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
  @JoinTable(name = "course_student",
      joinColumns = @JoinColumn(name = "course_id"),
      inverseJoinColumns = @JoinColumn(name = "student_id"))
  private List<Student> students;

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public Course() {
  }

  public Course(String title) {
    this.title = title;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Instructor getInstructor() {
    return instructor;
  }

  public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
  }

  // Add convenience method for uni-directional relationship
  public void addReview(Review review) {
    if (reviews == null) {
      reviews = new ArrayList<>();
    }
      reviews.add(review);
  }

  // Add convenience method
  public void addStudent(Student student) {
    if (students == null) {
      students = new ArrayList<>();
    }
    students.add(student);
  }

  @Override
  public String toString() {
    return "Course{" +
        "id=" + id +
        ", title='" + title + '\'' +
        '}';
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }
}
