package io.github.tthn0.demo.hibernate.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.tthn0.demo.hibernate.dao.CourseRepository;
import io.github.tthn0.demo.hibernate.dao.EnrollmentRepository;
import io.github.tthn0.demo.hibernate.dao.StudentRepository;
import io.github.tthn0.demo.hibernate.domain.Course;
import io.github.tthn0.demo.hibernate.domain.Enrollment;
import io.github.tthn0.demo.hibernate.domain.Student;
import io.github.tthn0.demo.hibernate.dto.EnrollmentWriteDto;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepo;
    private final CourseRepository courseRepo;
    private final StudentRepository studentRepo;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepo, CourseRepository courseRepo,
            StudentRepository studentRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    @Transactional(readOnly = true)
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepo.findAll();
    }

    @Transactional
    public Enrollment insertEnrollment(EnrollmentWriteDto dto) {
        List<Enrollment> potentialExistingEnrollments = enrollmentRepo.findByStudentIdAndCourseId(dto.getStudentId(),
                dto.getCourseId());

        if (!potentialExistingEnrollments.isEmpty()) {
            throw new RuntimeException("Student is already enrolled in this course");
        }

        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Enrollment enrollment = new Enrollment(
                LocalDate.now(), "100", student, course);

        return enrollmentRepo.save(enrollment);
    }
}
