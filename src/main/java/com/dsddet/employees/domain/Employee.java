package com.dsddet.employees.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "EMPLOYEES")
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="employeeid")
    private String employeeId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @CreationTimestamp
    @Column(name = "startdate")
    private Date startDate;

    @Column(name = "department")
    private String department;

    @Column(name = "jobtitle")
    private String jobTitle;

    @Column(name = "email")
    private String email;

}
