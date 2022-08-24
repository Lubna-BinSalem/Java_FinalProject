package com.example.bookatable.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @Data @Entity @NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name can't be empty")
    @Column(unique = true)
    private String name;

    @NotEmpty(message = "Logo image can't be empty")
    @URL(message = "Logo image must be a URL")
    @Column(columnDefinition = "TEXT",nullable = false)
    private String image;

    @NotEmpty(message = "Menu can't be empty")
    @URL(message = "Menu must be a URL")
    @Column(columnDefinition = "TEXT",nullable = false)
    private String menu;

    @NotEmpty(message = "Phone number can't be empty")
    private String phoneNumber;

    @NotEmpty(message = "Description can't be empty")
    @Column(columnDefinition = "TEXT",nullable = false)
    private String description;

    @NotEmpty(message = "Address can't be empty")
    private String address;

//    @NotEmpty(message = "The start time can't be empty")
//    private String startTime;
//
//    @NotEmpty(message = "The end time can't be empty")
//    private String endTime;

    @NotNull(message = "The ownerID can't be empty")
    private Integer ownerID;

}
