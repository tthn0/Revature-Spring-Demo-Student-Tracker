package io.github.tthn0.demo.hibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.tthn0.demo.hibernate.domain.Enrollment;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    List<Enrollment> findByStudentIdAndCourseId(Integer studentId, Integer courseId);

}
