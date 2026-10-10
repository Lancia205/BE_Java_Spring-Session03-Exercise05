package com.example.session03.model;

public class Course {
    private int id;
    private String title;
    private String status;
    private int instructorId;

    public Course(int id, String title, String status, int instructorId) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.instructorId = instructorId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }
}

