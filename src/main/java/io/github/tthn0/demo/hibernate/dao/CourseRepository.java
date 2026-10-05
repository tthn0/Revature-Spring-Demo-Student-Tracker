package io.github.tthn0.demo.hibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.tthn0.demo.hibernate.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
