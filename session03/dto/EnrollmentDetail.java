
package com.example.session03.dto;

import com.example.session03.model.Course;
import com.example.session03.model.Enrollment;

public class EnrollmentDetail {
    private Enrollment enrollment;
    private Course course;

    public EnrollmentDetail() {
    }

    public EnrollmentDetail(Enrollment enrollment, Course course) {
        this.enrollment = enrollment;
        this.course = course;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
