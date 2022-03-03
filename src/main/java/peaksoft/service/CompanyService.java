package peaksoft.service;

import peaksoft.model.Company;

import java.util.List;

public interface CompanyService {
    void saveCompany(Company company);
    List<Company> companyList();
    void updateCompany(Company company);
    void deleteCompany(Long id);
    Company getById(Long id);
}
