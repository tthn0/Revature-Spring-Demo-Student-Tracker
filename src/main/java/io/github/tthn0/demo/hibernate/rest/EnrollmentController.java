package io.github.tthn0.demo.hibernate.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.tthn0.demo.hibernate.dto.EnrollmentWriteDto;
import io.github.tthn0.demo.hibernate.domain.Course;
import io.github.tthn0.demo.hibernate.domain.Enrollment;
import io.github.tthn0.demo.hibernate.service.CourseService;
import io.github.tthn0.demo.hibernate.service.EnrollmentService;
import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public List<Enrollment> getEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@Valid @RequestBody EnrollmentWriteDto dto) {
        Enrollment createdEnrollment = enrollmentService.insertEnrollment(dto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdEnrollment.getId())
                .toUri())
                .body(createdEnrollment);
    }
}