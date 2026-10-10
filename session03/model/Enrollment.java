package com.example.session03.model;


public class Enrollment {
    private int id;
    private String studentName;
    private int courseId;

    public Enrollment(int id, String studentName, int courseId) {
        this.id = id;
        this.studentName = studentName;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}

