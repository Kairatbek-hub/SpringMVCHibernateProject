package peaksoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class TeacherDaoImpl implements TeacherDao {
    private final EntityManagerFactory entityManagerFactory;
    @Autowired
    public TeacherDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
    public void saveTeacher(Teacher teacher) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(teacher);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Teacher> teacherList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Teacher> teachers = entityManager.createQuery("select t from Teacher t").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return teachers;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(teacher);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteTeacher(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Teacher teacher = entityManager.find(Teacher.class, id);
        entityManager.remove(teacher);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Teacher teacher = entityManager.find(Teacher.class, id);
        entityManager.getTransaction().commit();
        return teacher;
    }
}
