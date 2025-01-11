package com.example.internship.service;

import com.example.internship.entity.Review;
import com.example.internship.entity.UserInfo;
import com.example.internship.repository.ReviewRepository;
import com.example.internship.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public Review addReview(String username, String company, int rating, String reviewText) throws IllegalAccessException {
        UserInfo user = userInfoRepository.findByName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!"UBB".equalsIgnoreCase(user.getCompany())) {
            throw new IllegalAccessException("Only students can add reviews.");
        }

        Review review = new Review(user, company, rating, reviewText, new Date());
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByCompany(String company) {
        return reviewRepository.findByCompany(company);
    }

    public double getAverageRating(String company) {
        List<Review> reviews = reviewRepository.findByCompany(company);
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
    }
}

