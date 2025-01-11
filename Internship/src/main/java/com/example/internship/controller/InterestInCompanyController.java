package com.example.internship.controller;

import com.example.internship.entity.InterestInCompany;
import com.example.internship.service.InterestInCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interests")
public class InterestInCompanyController {

    @Autowired
    private InterestInCompanyService interestInCompanyService;

    @PostMapping("/express")
    public InterestInCompany expressInterest(
            @RequestParam String username,
            @RequestParam String company) throws IllegalAccessException {
        return interestInCompanyService.expressInterest(username, company);
    }

    @GetMapping("/company/{company}")
    public List<InterestInCompany> getInterestsByCompany(@PathVariable String company) {
        return interestInCompanyService.getInterestsByCompany(company);
    }

    @GetMapping("/student/{studentId}")
    public List<InterestInCompany> getInterestsByStudent(@PathVariable Long studentId) {
        return interestInCompanyService.getInterestsByStudent(studentId);
    }
}

