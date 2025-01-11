package com.example.internship.repository;

import com.example.internship.entity.Internship;
import com.example.internship.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InternshipRepository extends JpaRepository<Internship, Long>{
}
