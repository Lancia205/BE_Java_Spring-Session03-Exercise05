package com.example.session03.controller;

import com.example.session03.dto.EnrollCourseRequest;
import com.example.session03.dto.EnrollmentDetail;
import java.util.NoSuchElementException;
import com.example.session03.model.ApiResponse;
import com.example.session03.model.Enrollment;
import com.example.session03.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> findAll() {
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Lấy danh sách thành công",
                service.getAllEnrollments()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> findById(
            @PathVariable long id) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(
                    true, "Tìm thấy Enrollment",
                    service.findEnrollmentById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> create(
            @RequestBody Enrollment enrollment) {
        return ResponseEntity.status(201).body(new ApiResponse<>(
                true, "Thêm thành công",
                service.createEnrollment(enrollment)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> update(
            @PathVariable long id, @RequestBody Enrollment enrollment) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(
                    true, "Cập nhật thành công",
                    service.updateEnrollment(id, enrollment)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable long id) {
        try {
            service.deleteEnrollmentById(id);
            return ResponseEntity.ok(new ApiResponse<>(
                    true, "Xóa thành công", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PostMapping("/enroll-course")
    public ResponseEntity<ApiResponse<EnrollmentDetail>> enrollCourse(
            @RequestBody EnrollCourseRequest request) {
        try {
            EnrollmentDetail result = service.enrollCourse(request);

            return ResponseEntity.status(201).body(
                    new ApiResponse<>(
                            true,
                            "Đăng ký khóa học thành công",
                            result
                    )
            );
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, e.getMessage(), null)
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, e.getMessage(), null)
            );
        }
    }

}
