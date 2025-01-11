package com.example.internship.entity;

import com.example.internship.entity.enums.Technology;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Internship {
    @Id
    @GeneratedValue
    private Long id;

    private Date startDate;
    private Date endDate;
    private Boolean paid;
    private Technology technology;
    private Boolean open;
    private String company;
}
