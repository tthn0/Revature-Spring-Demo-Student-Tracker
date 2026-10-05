package io.github.tthn0.demo.hibernate.dao;

import java.util.List;

import io.github.tthn0.demo.hibernate.domain.Student;

public interface StudentDao {
    Student insert(Student student);

    List<Student> findAll();

    Student findById(Integer id);

    void delete(Student student);

    List<Student> findByLastName(String lastName);

}
