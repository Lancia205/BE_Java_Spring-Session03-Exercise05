
package com.example.session03.controller;

import com.example.session03.model.ApiResponse;
import com.example.session03.model.Course;
import com.example.session03.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> findAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lấy danh sách thành công",
                        service.getAllCourses()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(
                    true, "Tìm thấy Course", service.findCourseById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(@RequestBody Course course) {
        return ResponseEntity.status(201).body(
                new ApiResponse<>(true, "Thêm thành công",
                        service.createCourse(course)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> update(
            @PathVariable long id, @RequestBody Course course) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(
                    true, "Cập nhật thành công",
                    service.updateCourse(id, course)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable long id) {
        try {
            service.deleteCourseById(id);
            return ResponseEntity.ok(new ApiResponse<>(
                    true, "Xóa thành công", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}
