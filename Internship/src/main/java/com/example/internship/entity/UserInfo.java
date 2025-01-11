package com.example.internship.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String fullName;
    private String email;
    private String password;
    private String company;
    private String resumeUrl;
    private String githubUrl;

    private String roles;
    private boolean enabled;
}
