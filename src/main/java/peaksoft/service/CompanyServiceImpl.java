package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CompanyDao;
import peaksoft.model.Company;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public void saveCompany(Company company) {
        companyDao.saveCompany(company);
    }

    @Override
    public List<Company> companyList() {
        return companyDao.companyList();
    }

    @Override
    public void updateCompany(Company company) {
        companyDao.updateCompany(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyDao.deleteCompany(id);
    }

    @Override
    public Company getById(Long id) {
        return companyDao.getById(id);
    }
}
