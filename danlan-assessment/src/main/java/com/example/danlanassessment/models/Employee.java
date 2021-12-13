package com.example.danlanassessment.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String title;
    private String company;
    private List<Project> projects;
}
