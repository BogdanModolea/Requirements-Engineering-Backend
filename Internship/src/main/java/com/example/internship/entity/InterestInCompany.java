package com.example.internship.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class InterestInCompany {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo student; // The student expressing interest

    @Column(nullable = false)
    private String company; // The company of interest

    // Constructors, Getters, Setters
    public InterestInCompany() {
    }

    public InterestInCompany(UserInfo student, String company) {
        this.student = student;
        this.company = company;
    }
}
