package com.siva.jobapp.company.impl;

import com.siva.jobapp.company.Company;
import com.siva.jobapp.company.CompanyRepository;
import com.siva.jobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company c = companyOptional.get();
            c.setName(company.getName());
            c.setDescription(company.getDescription());
            c.setJobs(company.getJobs());
            companyRepository.save(c);
            return true;
        }
        return  false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteById(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return  true;
        }
        else return false;

    }

    @Override
    public Company findById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent())
            return companyOptional.get();
        return null;
    }
}
