package io.github.tthn0.demo.hibernate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.github.tthn0.demo.hibernate.domain.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class EntityManagerStudentDao implements StudentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Student insert(Student student) {
        em.persist(student);
        return student;
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s ORDER BY s.id",
                Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    @Override
    public void delete(Student student) {
        em.remove(student);
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = em.createQuery("""
                SELECT s FROM Student s
                WHERE s.lastName = :lastName
                ORDER BY s.id
                """,
                Student.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
}
