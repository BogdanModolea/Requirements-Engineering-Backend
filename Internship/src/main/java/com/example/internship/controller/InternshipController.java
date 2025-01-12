package com.example.internship.controller;

import com.example.internship.entity.Internship;
import com.example.internship.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/internship")
public class InternshipController {
    @Autowired
    private InternshipService internshipService;

    @PostMapping("/saveInternship")
    public void saveInternship(@RequestBody Internship internship, @RequestHeader("Authorization") String authorizationHeader) {
        internshipService.addInternship(internship, authorizationHeader);
    }

    @GetMapping("/viewInternship/{id}")
    public Optional<Internship> getInternshipById(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        return internshipService.getInternshipById(id, authorizationHeader);
    }

    @GetMapping("/listInternships")
    public List<Internship> getAllInternships(@RequestHeader("Authorization") String authorizationHeader) {
        return internshipService.getAllInternships(authorizationHeader);
    }

    @GetMapping("/filter")
    public List<Internship> filterInternships(
            @RequestParam(required = false) String technology,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) Boolean open,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate
    ) {
        return internshipService.filterInternships(technology, paid, open, company, startDate, endDate);
    }
}
