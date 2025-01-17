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

    @GetMapping("/user")
    public List<Application> getApplicationsByUser(@RequestHeader("Authorization") String authorizationHeader) {
        return applicationService.getApplicationsByUser(authorizationHeader);
    }

    @GetMapping("/internship/{internshipId}")
    public List<Application> getApplicationsByInternship(@PathVariable Long internshipId) {
        return applicationService.getApplicationsByInternship(internshipId);
    }

    @GetMapping("/canApply/{internshipId}")
    public Boolean canStudentApply(@PathVariable Long internshipId, @RequestHeader("Authorization") String authorizationHeader) {
        return applicationService.canStudentApply(internshipId, authorizationHeader);
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
