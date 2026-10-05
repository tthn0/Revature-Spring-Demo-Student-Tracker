package io.github.tthn0.demo.hibernate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class EnrollmentWriteDto {
    @NotNull
    @JsonProperty("course_id")
    private Integer courseId;
    
    @NotNull
    @JsonProperty("student_id")
    private Integer studentId;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
