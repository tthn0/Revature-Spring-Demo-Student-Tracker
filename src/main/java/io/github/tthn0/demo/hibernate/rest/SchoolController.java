package io.github.tthn0.demo.hibernate.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.tthn0.demo.hibernate.domain.School;
import io.github.tthn0.demo.hibernate.service.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {
    private final StudentService studentService;

    public SchoolController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<School> insertSchool(@Valid @RequestBody School school) {
        School savedSchool = studentService.insertSchool(school);
        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(savedSchool.getId())
                                .toUri())
                .body(savedSchool);
    }

    @GetMapping("/{id}")
    public School getSchoolById(@PathVariable Integer id) {
        return studentService.getSchoolById(id);
    }
}