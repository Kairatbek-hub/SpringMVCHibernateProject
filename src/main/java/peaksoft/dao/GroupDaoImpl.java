package peaksoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class GroupDaoImpl implements GroupDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public GroupDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void saveGroup(Group group) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(group);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Group> groupList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Group> groups = entityManager.createQuery("select g from Group g").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return groups;
    }

    @Override
    public void updateGroup(Group group) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(group);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteGroup(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Group group = entityManager.find(Group.class, id);
        entityManager.remove(group);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Group getGroupId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Group group = entityManager.find(Group.class, id);
        entityManager.getTransaction().commit();
        return group;
    }
}
