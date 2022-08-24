package com.example.bookatable.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor @Data @AllArgsConstructor @Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "booking date is required")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date bookingDate;

    @NotNull(message = "persons can't be null")
    private Integer numOfPerson;

    @NotEmpty(message = "Request must be filled, if no request input 'no request'")
    private String request;

    @NotNull(message = "The restaurant id can't be null")
    private Integer restID;

    @NotNull(message = "The user id can't be null")
    private Integer userID;

}
