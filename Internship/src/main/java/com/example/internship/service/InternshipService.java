package com.example.internship.service;

import com.example.internship.entity.Internship;
import com.example.internship.entity.enums.Technology;
import com.example.internship.repository.InternshipRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InternshipService {
    @Autowired
    private InternshipRepository internshipRepository;
    @Autowired
    private EntityManager entityManager;

    public void addInternship(Internship internship, String authorizationHeader) {
        internship.setOpen(true);
        internshipRepository.save(internship);
    }

    public Optional<Internship> getInternshipById(Long id, String authorizationHeader) {
        return internshipRepository.findById(id);
    }

    public List<Internship> getAllInternships(String authorizationHeader) {
        return internshipRepository.findAll();
    }

    public List<Internship> filterInternships(String technology, Boolean paid, Boolean open, String company, Date startDate, Date endDate) {
        // Create the base query
        StringBuilder queryString = new StringBuilder("SELECT i FROM Internship i WHERE 1=1");
        Map<String, Object> parameters = new HashMap<>();

        // Add conditions dynamically
        if (technology != null) {
            queryString.append(" AND i.technology = :technology");
            parameters.put("technology", Technology.valueOf(technology));
        }
        if (paid != null) {
            queryString.append(" AND i.paid = :paid");
            parameters.put("paid", paid);
        }
        if (open != null) {
            queryString.append(" AND i.open = :open");
            parameters.put("open", open);
        }
        if (company != null && !company.isEmpty()) {
            queryString.append(" AND i.company = :company");
            parameters.put("company", company);
        }
        if (startDate != null) {
            queryString.append(" AND i.startDate = :startDate");
            parameters.put("startDate", startDate);
        }
        if (endDate != null) {
            queryString.append(" AND i.endDate = :endDate");
            parameters.put("endDate", endDate);
        }

        // Create the query
        TypedQuery<Internship> query = entityManager.createQuery(queryString.toString(), Internship.class);

        // Set parameters
        parameters.forEach(query::setParameter);

        return query.getResultList();
    }
}
