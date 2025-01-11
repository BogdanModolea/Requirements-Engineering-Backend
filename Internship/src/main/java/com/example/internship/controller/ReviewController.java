package com.example.internship.controller;

import com.example.internship.entity.Review;
import com.example.internship.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public Review addReview(
            @RequestParam String username,
            @RequestParam String company,
            @RequestParam int rating,
            @RequestParam(required = false) String reviewText) throws IllegalAccessException {
        return reviewService.addReview(username, company, rating, reviewText);
    }

    @GetMapping("/company/{company}")
    public List<Review> getReviewsByCompany(@PathVariable String company) {
        return reviewService.getReviewsByCompany(company);
    }

    @GetMapping("/company/{company}/average-rating")
    public double getAverageRating(@PathVariable String company) {
        return reviewService.getAverageRating(company);
    }
}
