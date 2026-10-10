
package com.example.session03.service;

import com.example.session03.model.Instructor;
import com.example.session03.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    private final InstructorRepository repository;

    public InstructorService(InstructorRepository repository) {
        this.repository = repository;
    }

    public List<Instructor> getAllInstructors() {
        return repository.findAll();
    }

    public Instructor findInstructorById(long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy Instructor có id: " + id));
    }

    public Instructor createInstructor(Instructor instructor) {
        return repository.create(instructor);
    }

    public Instructor updateInstructor(long id, Instructor instructor) {
        return repository.update(id, instructor);
    }

    public void deleteInstructorById(long id) {
        repository.deleteById(id);
    }
}
