package com.example.internship.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo reviewer; // The student writing the review

    @Column(nullable = false)
    private String company; // The reviewed company (derived from UserInfo.company)

    @Column(nullable = false)
    private int rating; // Rating out of 5

    @Column(length = 1000)
    private String reviewText; // Review content

    private Date reviewDate;

    // Constructors, Getters, Setters
    public Review() {}

    public Review(UserInfo reviewer, String company, int rating, String reviewText, Date reviewDate) {
        this.reviewer = reviewer;
        this.company = company;
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }
}
