package com.example.internship.controller;

import com.example.internship.entity.Application;
import com.example.internship.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public Application applyForInternship(
            @RequestParam Long userId,
            @RequestParam Long internshipId) {
        return applicationService.applyForInternship(userId, internshipId);
    }

    @GetMapping("/user/{userId}")
    public List<Application> getApplicationsByUser(@PathVariable Long userId) {
        return applicationService.getApplicationsByUser(userId);
    }

    @GetMapping("/internship/{internshipId}")
    public List<Application> getApplicationsByInternship(@PathVariable Long internshipId) {
        return applicationService.getApplicationsByInternship(internshipId);
    }

    @PostMapping("/accept/{applicationId}")
    public Application acceptApplication(@PathVariable Long applicationId, @RequestHeader("Authorization") String authorizationHeader) throws IllegalAccessException {
        return applicationService.acceptApplication(applicationId, authorizationHeader);
    }

    @PostMapping("/reject/{applicationId}")
    public Application rejectApplication(@PathVariable Long applicationId, @RequestHeader("Authorization") String authorizationHeader) throws IllegalAccessException {
        return applicationService.rejectApplication(applicationId, authorizationHeader);
    }
}
