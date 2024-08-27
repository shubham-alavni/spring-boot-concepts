package com.lead.cruddemo.dao;

import com.lead.cruddemo.entity.Course;
import com.lead.cruddemo.entity.Instructor;
import com.lead.cruddemo.entity.InstructorDetail;
import com.lead.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    // define entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor instructor){
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        // default behavior of one-to-one fetch type is eager
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        // Get courses from instructor
        List<Course> courses = instructor.getCourses();

        // Break association between instructor and courses
        courses.forEach(course -> course.setInstructor(null));

        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        // remove the associated object reference
        // break bi-directional link
        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByIstructorId(int id) {
//        TypedQuery<Course> query = entityManager.createQuery("FROM Course where instructor_id = :id", Course.class);
//        query.setParameter("id", id);
//        List<Course> courses = query.getResultList();
//        return courses;
         return entityManager.createQuery("FROM Course where instructor.id = :id", Course.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        return entityManager.createQuery("SELECT i FROM Instructor i " +
                        "JOIN FETCH i.courses " +
                        "JOIN FETCH i.instructorDetail " +
                        "WHERE i.id = :id", Instructor.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsById(int id) {
        // Create query
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c " +
                "JOIN FETCH c.reviews " +
                "WHERE c.id = :id", Course.class);
        query.setParameter("id", id);
        // Execute query and get result
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentsById(int id) {
        // Create query
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c " +
                "JOIN FETCH c.students " +
                "WHERE c.id = :id", Course.class);
        query.setParameter("id", id);

        // Execute query and get result
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesById(int id) {
        // Create query
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s " +
                "JOIN FETCH s.courses " +
                "WHERE s.id = :id", Student.class);
        query.setParameter("id", id);

        // Execute query and get result
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        entityManager.remove(entityManager.find(Student.class, id));
    }


}
