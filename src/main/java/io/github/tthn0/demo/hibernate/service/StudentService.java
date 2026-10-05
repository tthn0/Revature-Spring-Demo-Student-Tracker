package io.github.tthn0.demo.hibernate.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.tthn0.demo.hibernate.dao.CourseRepository;
import io.github.tthn0.demo.hibernate.dao.EnrollmentRepository;
import io.github.tthn0.demo.hibernate.dao.SchoolRepository;
import io.github.tthn0.demo.hibernate.dao.StudentDao;
import io.github.tthn0.demo.hibernate.dao.StudentRepository;
import io.github.tthn0.demo.hibernate.domain.School;
import io.github.tthn0.demo.hibernate.domain.Student;
import io.github.tthn0.demo.hibernate.dto.StudentWriteDto;
import io.github.tthn0.demo.hibernate.exceptions.RecordNotFoundException;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentDao studentDao;
    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final SchoolRepository schoolRepo;
    private final StudentRepository studentRepo;

    @Autowired
    public StudentService(
            @Qualifier("entityManagerStudentDao") StudentDao studentDao,
            CourseRepository courseRepo,
            EnrollmentRepository enrollmentRepo,
            SchoolRepository schoolRepo,
            StudentRepository studentRepo) {
        this.studentDao = studentDao;
        this.courseRepo = courseRepo;
        this.enrollmentRepo = enrollmentRepo;
        this.schoolRepo = schoolRepo;
        this.studentRepo = studentRepo;
    }

    @Transactional
    public Student insertStudent(StudentWriteDto student) {
        // return studentDao.insert(student);

        School school = schoolRepo.findById(student.getSchoolId())
                .orElse(null);

        // School school = student.getSchoolId() != null ?
        //         getSchoolById(student.getSchoolId()) : null;

        Student newStudent = new Student(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                school
        );

        return studentRepo.save(newStudent);
    }

    @Transactional
    public School insertSchool(School school) {
        return schoolRepo.save(school);
    }

    @Transactional(readOnly = true)
    public List<Student> findStudentsBySchoolName(String schoolName) {
        return studentRepo.findBySchool_Name(schoolName);
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents(int page, int count, boolean asc) {
        Sort.Direction direction = asc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, count, Sort.by(direction, "id"));
        return studentRepo.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        // return studentDao.findAll();
        return studentRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Student getStudentById(Integer id) {
        // Student student = studentDao.findById(id);
        // if (student == null) {
        // throw new RecordNotFoundException(
        // String.format("Student not found with ID: %s", id));
        // }
        // return student;

        return studentRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(
                        String.format("Student not found with ID: %s", id)));
    }

    @Transactional
    public void deleteStudent(Integer id) {
        // Student student = getStudentById(id);
        // studentDao.delete(student);

        Student student = getStudentById(id);
        studentRepo.delete(student);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsByLastName(String lastName) {
        // return studentDao.findByLastName(lastName);
        return studentRepo.findByLastName(lastName);
    }

    /**
     * Don't need a call to DAO here since this is in a
     * transaction and `existingStudent` is a managed
     * entity. Any changes to it will be tracked before
     * the transaction closes, and dirty checking will
     * occur. The managed entity will detect the change,
     * they will be auto-committed.
     */

    @Transactional
    public Student updateStudent(Integer id, Student student) {
        // Student existingStudent = getStudentById(id);
        // existingStudent.setFirstName(student.getFirstName());
        // existingStudent.setLastName(student.getLastName());
        // existingStudent.setEmail(student.getEmail());
        // return existingStudent;

        Student existingStudent = getStudentById(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        return existingStudent; // Exact same
    }

    @Transactional(readOnly = true)
    public School getSchoolById(Integer id) {
        return schoolRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(
                        String.format("School not found with ID: %s", id)));
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsWhereIdIsAMultipleOf(int multiple) {
       return studentRepo.findStudentsWhereIdIsAMultipleOf(multiple);
    }
}
