
package com.example.session03.repository;

import com.example.session03.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>(List.of(
            new Enrollment(1, "Nguyen Van C", 1),
            new Enrollment(2, "Tran Thi D", 2)
    ));

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Optional<Enrollment> findById(long id) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId() == id)
                .findFirst();
    }

    public Enrollment create(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment update(long id, Enrollment newEnrollment) {
        Enrollment enrollment = findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy Enrollment có id: " + id));

        enrollment.setStudentName(newEnrollment.getStudentName());
        enrollment.setCourseId(newEnrollment.getCourseId());
        return enrollment;
    }

    public void deleteById(long id) {
        Enrollment enrollment = findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy Enrollment có id: " + id));

        enrollments.remove(enrollment);
    }
}
