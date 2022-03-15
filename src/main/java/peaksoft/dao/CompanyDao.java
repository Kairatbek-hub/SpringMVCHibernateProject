package peaksoft.dao;

import peaksoft.model.Company;

import java.util.List;

public interface CompanyDao {
    void saveCompany(Company company);
    List<Company> companyList();
    void updateCompany(Company company);
    void deleteCompany(Long id);
    Company getCompanyById(Long id);
}
