package peaksoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CourseDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void saveCourse(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Course> courseList(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Course> courses = entityManager.createQuery("select c from Course c where c.company.id = :id").setParameter("id",id).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return courses;
    }

    @Override
    public List<Course> courseList() {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            List<Course> courses = entityManager.createQuery("select c from Course c").getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return courses;
    }

    @Override
    public void updateCourse(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(course);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteCourse(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Course getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Course course = entityManager.find(Course.class, id);
        entityManager.getTransaction().commit();
        return course;
    }
}
