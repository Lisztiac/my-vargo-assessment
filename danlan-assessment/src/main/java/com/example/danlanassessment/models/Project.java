package com.example.danlanassessment.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    private Long id;
    private String codeName;
    private Date startDate;
    private Date endDate;
}
