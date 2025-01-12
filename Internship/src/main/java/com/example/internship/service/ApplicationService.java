package com.example.internship.service;

import com.example.internship.entity.Application;
import com.example.internship.entity.Internship;
import com.example.internship.entity.UserInfo;
import com.example.internship.entity.enums.ApplicationStatus;
import com.example.internship.repository.ApplicationRepository;
import com.example.internship.repository.InternshipRepository;
import com.example.internship.repository.UserInfoRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private JwtService jwtService;

    public Application applyForInternship(Long userId, Long internshipId) {
        UserInfo user = userInfoRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new IllegalArgumentException("Internship not found"));

        Application application = new Application(user, internship, new Date(), ApplicationStatus.PENDING);
        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByUser(String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(token);
        UserInfo user = userInfoRepository.findByName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Long userId = user.getId();

        return applicationRepository.findAll().stream()
                .filter(app -> app.getUser().getId().equals(userId))
                .toList();
    }

    public List<Application> getApplicationsByInternship(Long internshipId) {
        return applicationRepository.findAll().stream()
                .filter(app -> app.getInternship().getId().equals(internshipId))
                .toList();
    }

    public Boolean canStudentApply(Long internshipId, String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(token);
        UserInfo user = userInfoRepository.findByName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return applicationRepository.findAll().stream()
                .filter(app -> app.getInternship().getId().equals(internshipId) && app.getUser().getId().equals(user.getId()))
                .noneMatch(app ->
                        app.getStatus().equals(ApplicationStatus.PENDING) ||
                                app.getStatus().equals(ApplicationStatus.APPROVED)
                );
    }


    public Application acceptApplication(Long applicationId, String authorizationHeader) throws IllegalAccessException {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(token);
        UserInfo user = userInfoRepository.findByName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if ("UBB".equalsIgnoreCase(user.getCompany())) {
            throw new IllegalAccessException("Users from 'UBB' are not allowed to accept or reject applications.");
        }

        application.setStatus(ApplicationStatus.APPROVED);
        return applicationRepository.save(application);
    }

    public Application rejectApplication(Long applicationId, String authorizationHeader) throws IllegalAccessException {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(token);
        UserInfo user = userInfoRepository.findByName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if ("UBB".equalsIgnoreCase(user.getCompany())) {
            throw new IllegalAccessException("Users from 'UBB' are not allowed to accept or reject applications.");
        }

        application.setStatus(ApplicationStatus.REJECTED);
        return applicationRepository.save(application);
    }
}
