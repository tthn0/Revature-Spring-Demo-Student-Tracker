package io.github.tthn0.demo.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import io.github.tthn0.demo.hibernate.dao.CourseRepository;
import io.github.tthn0.demo.hibernate.dao.EnrollmentRepository;
import io.github.tthn0.demo.hibernate.dao.SchoolRepository;
import io.github.tthn0.demo.hibernate.dao.StudentDao;
import io.github.tthn0.demo.hibernate.dao.StudentRepository;
import io.github.tthn0.demo.hibernate.domain.Course;
import io.github.tthn0.demo.hibernate.dto.StudentWriteDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepo;

    @Autowired
    public CourseService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }
    
    @Transactional(readOnly=true)
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }
    
    @Transactional
    public Course insertCourse(Course course) {
        return courseRepo.save(course);
    }
}
