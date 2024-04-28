package com.siva.jobapp.company;

import com.siva.jobapp.job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanie(@PathVariable Long id, @RequestBody Company company){
        boolean updated = companyService.updateCompany(id,company);
        if(updated)
            return new ResponseEntity<>("Company updated Successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity("Company Added Successfully", HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean deleted = companyService.deleteById(id);
        if(deleted)
            return new ResponseEntity<>("Company deleted Successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id){
        Company c = companyService.findById(id);
        if(c != null)
            return new ResponseEntity<>(c,HttpStatus.OK) ;
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
