package io.github.tthn0.demo.hibernate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import io.github.tthn0.demo.hibernate.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByLastName(String lastName);

    List<Student> findBySchool_Name(String schoolName);

    Page<Student> findAll(Pageable pageable);

    @Query("SELECT s FROM Student s WHERE MOD(s.id, :multiple) = 0")
    List<Student> findStudentsWhereIdIsAMultipleOf(@Param("multiple") int multiple);
}
