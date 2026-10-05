package io.github.tthn0.demo.hibernate.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.tthn0.demo.hibernate.domain.School;
import io.github.tthn0.demo.hibernate.domain.Student;
import io.github.tthn0.demo.hibernate.dto.StudentWriteDto;
import io.github.tthn0.demo.hibernate.service.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents(
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int count,
        @RequestParam(defaultValue="true") boolean asc
    ) {
        return studentService.getAllStudents(page, count, asc);
    }

    @PostMapping
    public ResponseEntity<Student> insertStudent(@Valid @RequestBody StudentWriteDto student) {

        Student savedStudent = studentService.insertStudent(student);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedStudent.getId())
                        .toUri())
                .body(savedStudent);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @GetMapping(params = "lastName")
    public List<Student> getStudentsByLastName(@RequestParam String lastName) {
        return studentService.getStudentsByLastName(lastName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @Valid @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @GetMapping(params = "school")
    public List<Student> getStudentsBySchoolName(@RequestParam String school) {
        return studentService.findStudentsBySchoolName(school);
    }

    @GetMapping(params = "whereIdIsAMultipleOf")
    public List<Student> getStudentsWhereIdIsAMultipleOf(@RequestParam int whereIdIsAMultipleOf) {
        return studentService.getStudentsWhereIdIsAMultipleOf(whereIdIsAMultipleOf);
    }
}
