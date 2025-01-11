package com.example.internship.repository;

import com.example.internship.entity.InterestInCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestInCompanyRepository extends JpaRepository<InterestInCompany, Long> {
    List<InterestInCompany> findByCompany(String company);
    List<InterestInCompany> findByStudentId(Long studentId);
}
