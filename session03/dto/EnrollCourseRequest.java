
package com.example.session03.dto;

public class EnrollCourseRequest {
    private String studentName;
    private long courseId;

    public EnrollCourseRequest() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
