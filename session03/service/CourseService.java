
package com.example.session03.service;

import com.example.session03.model.Course;
import com.example.session03.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    public Course findCourseById(long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy Course có id: " + id));
    }

    public Course createCourse(Course course) {
        return repository.create(course);
    }

    public Course updateCourse(long id, Course course) {
        return repository.update(id, course);
    }

    public void deleteCourseById(long id) {
        repository.deleteById(id);
    }
}
