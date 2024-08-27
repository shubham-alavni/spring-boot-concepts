package com.lead.cruddemo.dao;

import com.lead.cruddemo.entity.Course;
import com.lead.cruddemo.entity.Instructor;
import com.lead.cruddemo.entity.InstructorDetail;
import com.lead.cruddemo.entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    // find courses by instructor id
    List<Course> findCoursesByIstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor instructor);

    void updateCourse(Course course);

    Course findCourseById(int id);

    void deleteCourseById(int id);

    void saveCourse(Course course);

    Course findCourseAndReviewsById(int id);

    Course findCourseAndStudentsById(int id);

    Student findStudentAndCoursesById(int id);

    void update(Student student);

    void deleteStudentById(int id);
}
