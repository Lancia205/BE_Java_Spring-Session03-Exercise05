
package com.example.session03.controller;

import com.example.session03.model.ApiResponse;
import com.example.session03.model.Instructor;
import com.example.session03.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService service;

    public InstructorController(InstructorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> findAll() {
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Lấy danh sách thành công",
                service.getAllInstructors()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> findById(
            @PathVariable long id) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(
                    true, "Tìm thấy Instructor",
                    service.findInstructorById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> create(
            @RequestBody Instructor instructor) {
        return ResponseEntity.status(201).body(new ApiResponse<>(
                true, "Thêm thành công",
                service.createInstructor(instructor)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> update(
            @PathVariable long id, @RequestBody Instructor instructor) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(
                    true, "Cập nhật thành công",
                    service.updateInstructor(id, instructor)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable long id) {
        try {
            service.deleteInstructorById(id);
            return ResponseEntity.ok(new ApiResponse<>(
                    true, "Xóa thành công", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}
