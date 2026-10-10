
package com.example.session03.service;

import com.example.session03.dto.EnrollCourseRequest;
import com.example.session03.dto.EnrollmentDetail;
import com.example.session03.model.Course;
import com.example.session03.model.Enrollment;
import com.example.session03.repository.CourseRepository;
import com.example.session03.repository.EnrollmentRepository;
import com.example.session03.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            CourseRepository courseRepository,
            InstructorRepository instructorRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment findEnrollmentById(long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Không tìm thấy Enrollment có id: " + id));
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.create(enrollment);
    }

    public Enrollment updateEnrollment(long id, Enrollment enrollment) {
        return enrollmentRepository.update(id, enrollment);
    }

    public void deleteEnrollmentById(long id) {
        enrollmentRepository.deleteById(id);
    }

    public EnrollmentDetail enrollCourse(EnrollCourseRequest request) {
        // 1. Kiểm tra Course tồn tại
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() ->
                        new NoSuchElementException("Course không tồn tại"));

        // 2. Kiểm tra trạng thái Course
        if (course.getStatus() == null ||
                !course.getStatus().equalsIgnoreCase("ACTIVE")) {
            throw new IllegalArgumentException(
                    "Course chưa ở trạng thái ACTIVE");
        }

        // 3. Kiểm tra Instructor tồn tại
        instructorRepository.findById(course.getInstructorId())
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Instructor của Course không tồn tại"));

        // 4. Tạo Enrollment mới với ID tiếp theo
        int newId = enrollmentRepository.findAll().stream()
                .mapToInt(Enrollment::getId)
                .max()
                .orElse(0) + 1;

        Enrollment enrollment = new Enrollment(
                newId,
                request.getStudentName(),
                (int) request.getCourseId()
        );

        enrollmentRepository.create(enrollment);

        // 5. Trả về Enrollment kèm Course
        return new EnrollmentDetail(enrollment, course);
    }
}
