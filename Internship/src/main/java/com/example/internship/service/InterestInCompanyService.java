package com.example.internship.service;

import com.example.internship.entity.InterestInCompany;
import com.example.internship.entity.UserInfo;
import com.example.internship.repository.InterestInCompanyRepository;
import com.example.internship.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestInCompanyService {

    @Autowired
    private InterestInCompanyRepository interestInCompanyRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public InterestInCompany expressInterest(String username, String company) throws IllegalAccessException {
        UserInfo user = userInfoRepository.findByName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!"UBB".equalsIgnoreCase(user.getCompany())) {
            throw new IllegalAccessException("Only students can express interest in companies.");
        }

        InterestInCompany interest = new InterestInCompany(user, company);
        return interestInCompanyRepository.save(interest);
    }

    public List<InterestInCompany> getInterestsByCompany(String company) {
        return interestInCompanyRepository.findByCompany(company);
    }

    public List<InterestInCompany> getInterestsByStudent(Long studentId) {
        return interestInCompanyRepository.findByStudentId(studentId);
    }
}