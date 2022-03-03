package peaksoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CompanyDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void saveCompany(Company company) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(company);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Company> companyList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Company> companies = entityManager.createQuery("select c from Company c").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return companies;
    }

    @Override
    public void updateCompany(Company company) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(company);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteCompany(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Company company = entityManager.find(Company.class, id);
        entityManager.remove(company);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Company getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Company company = entityManager.find(Company.class, id);
        entityManager.getTransaction().commit();
        return company;
    }
}
