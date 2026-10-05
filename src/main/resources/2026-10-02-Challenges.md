# 2026-10-02 Challenges

- **Deadline**: 10/7/2026.

## Challenge 1

```java

/**
 * Challenge 1:
 * Create an endpoint, service method, and repo implementation
 * That uses `@Query` to return results to the client.
 * I don't care what it does.
 */

// `StudentController.java`
@GetMapping(params = "whereIdIsAMultipleOf")
public List<Student> getStudentsWhereIdIsAMultipleOf(@RequestParam int whereIdIsAMultipleOf) {
    return studentService.getStudentsWhereIdIsAMultipleOf(whereIdIsAMultipleOf);
}

// `StudentService.java`
@Transactional(readOnly = true)
public List<Student> getStudentsWhereIdIsAMultipleOf(int multiple) {
    return studentRepo.findStudentsWhereIdIsAMultipleOf(multiple);
}

// `StudentRepository.java`
@Query("SELECT s FROM Student s WHERE MOD(s.id, :multiple) = 0")
List<Student> findStudentsWhereIdIsAMultipleOf(@Param("multiple") int multiple);
```

## Challenge 2

```java
/**
 * Challenge 2:
 * Use DTOs so that you can insert enrollment records.
 * I.e., a student should be able to sign up for a course.
 */
```

There were a lot of new files that I had to add to complete this challenge (too many code snippets to paste here). The following files were needed to get this challenge to work:

- `src/main/java/io/github/tthn0/demo/hibernate/dto/EnrollmentWriteDto.java`.
- `src/main/java/io/github/tthn0/demo/hibernate/rest/CourseController.java`.
- `src/main/java/io/github/tthn0/demo/hibernate/rest/EnrollmentController.java`.
- `src/main/java/io/github/tthn0/demo/hibernate/service/CourseService.java`.
- `src/main/java/io/github/tthn0/demo/hibernate/service/EnrollmentService.java`.
