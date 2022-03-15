package peaksoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    private final EntityManagerFactory entityManagerFactory;
    @Autowired
    public StudentDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
    public void saveStudent(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Student> studentList(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Student> students = entityManager.createQuery("select s from Student s where s.group.id = :id").setParameter("id",id).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return students;
    }

    @Override
    public void updateStudent(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteStudent(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Student getStudentById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Student student = entityManager.find(Student.class, id);
        entityManager.getTransaction().commit();
        return student;
    }
}
