package com.dsddet.employees.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    @NotBlank
    private String firstName;

    @Column(name = "lastname")
    @NotBlank
    private String lastName;

    @CreationTimestamp
    @Column(name = "startdate")
    private Date startDate;

    @Column(name = "department")
    @NotBlank
    private String department;

    @Column(name = "jobtitle")
    @NotBlank
    private String jobTitle;

    @Column(name = "email")
    @NotBlank
    private String email;

}
