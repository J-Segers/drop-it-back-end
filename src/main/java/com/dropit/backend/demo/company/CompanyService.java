package com.dropit.backend.demo.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompanyService{

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) { this.companyRepository = companyRepository; }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getSingleCompany(Long id) {
        Optional<Company> checkCompany = companyRepository.findById(id);
        if(checkCompany.isEmpty()) {
            throw new IllegalStateException("The company with id: " + id + " does not exist!");
        }
        return  companyRepository.getById(id);
    }

    public void registerCompany(Company company) {
        Optional<Company> checkCompanyName = companyRepository.findCompanyByName(company.getCompanyName());
        if(checkCompanyName.isPresent()){
            throw new IllegalStateException("A company with the name: " + " already exists!");
        }
        companyRepository.save(company);
    }

    public void updateCompanyInfo(Long companyId, String companyName, String owner) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new IllegalStateException("Company with id: " + companyId + " does not exist!"));

        Optional<Company> checkIfNameAvailable = companyRepository.findCompanyByName(companyName);
        if(checkIfNameAvailable.isEmpty()) {
            throw new IllegalStateException("Company name is already in use!");
        }

        if(companyName != null && companyName.length() > 0 && !Objects.equals(company.getCompanyName(), companyName)) {
            company.setCompanyName(companyName);
        }

        if(owner != null && owner.length() > 0 && !Objects.equals(company.getOwner(), owner)){
            company.setOwner(owner);
        }
    }

    public void deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new IllegalStateException("Company does not exist!"));
        companyRepository.deleteById(companyId);
    }
}
