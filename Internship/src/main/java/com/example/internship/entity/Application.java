package com.example.internship.entity;


import com.example.internship.entity.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @ManyToOne
    @JoinColumn(name = "internship_id", nullable = false)
    private Internship internship;

    private Date applicationDate;

    private ApplicationStatus status;

    public Application(UserInfo user, Internship internship, Date applicationDate, ApplicationStatus status) {
        this.user = user;
        this.internship = internship;
        this.applicationDate = applicationDate;
        this.status = status;
    }
}
