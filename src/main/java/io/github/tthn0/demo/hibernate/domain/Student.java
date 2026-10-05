package io.github.tthn0.demo.hibernate.domain;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "student") // `name` is Technically optional
public class Student {

    /**
     * Strategy Types
     * IDENTITY: uses the database auto-increment/identity column
     * SEQUENCE: use the database-provided sequence
     * TABLE: JPA uses a table to simulate a sequence
     * AUTO: JPA/provider chooses the strategy automatically
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Enrollment> enrollments = new ArrayList<>();

    @Size(max = 20)
    @NotBlank
    @Column(name = "first_name", length = 45) // `name` is Technically optional
    private String firstName;

    @Size(max = 20)
    @NotBlank
    @Column(name = "last_name", length = 45) // `name` is Technically optional
    private String lastName;

    @Email
    @NotBlank // NOT (null, empty, or whitespace)
    @Column(name = "email", length = 45, nullable = false, unique = true) // `nullable` is TRUE by default
    private String email;

    public Student() {
    }

    public Student(String firstName, String lastName, @Email @NotBlank String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(String firstName, String lastName, @Email @NotBlank String email, School school) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.school = school;
    }

    public Integer getId() {
        return id;
    }

    // public void setId(Integer id) {
    // this.id = id;
    // }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Worth being aware of:
     * 
     * @NotNull
     * @NotBlank
     * @NotEmpty
     * @Size
     * @Min
     * @Max
     * @Positive
     * @PositiveOrZero
     * @Pattern
     */

    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
        enrollment.setStudent(this);
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollments.remove(enrollment);
        enrollment.setStudent(null);
    }
}
