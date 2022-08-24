package com.example.bookatable.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.internal.engine.messageinterpolation.InterpolationTerm;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data @Entity @NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "RestaurantID can't be null")
    private Integer restID;

    @NotNull(message = "User ID can't be null")
    private Integer userID;

    @NotEmpty(message = "message can't be empty")
    @Column(columnDefinition = "TEXT",nullable = false)
    @Size(min = 2, message = "message have to be at least 2 characters long")
    private String message;

    @NotNull(message = "rate can't be null")
    @Range(min = 1, max = 5, message = "rate must be a number between 1 - 5")
    private Integer rate;

}
