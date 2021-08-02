package com.dropit.backend.demo.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "{companyId}")
    public Company getSingleCompany(@PathVariable("companyId") Long companyId) {
        return companyService.getSingleCompany(companyId);
    }

    @PostMapping
    public void registerCompany(@RequestBody Company company) {
        companyService.registerCompany(company);
    }

    @PutMapping(path = "{companyId}")
    public void updateCompanyInfo(
            @PathVariable("companyId") Long companyId,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String owner) {
    companyService.updateCompanyInfo( companyId, companyName, owner);
    }

    @DeleteMapping(path = "{companyId}")
    public void deleteCompany(@PathVariable("companyId") Long companyId) {
        companyService.deleteCompany(companyId);
    }
}
