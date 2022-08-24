package com.example.bookatable.Controller;

import com.example.bookatable.Model.Api;
import com.example.bookatable.Model.Booking;
import com.example.bookatable.Model.User;
import com.example.bookatable.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/booking")
public class BookingController {

    private final BookingService bookingService;


    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings(@AuthenticationPrincipal User user) {
        List<Booking> bookings = bookingService.getAllBookings(user);
        return ResponseEntity.status(200).body(bookings);
    }
    @PostMapping("/add")
    public ResponseEntity addBooking(@RequestBody Booking booking){
        bookingService.addBooking(booking);
        return ResponseEntity.status(201).body(new Api("Booking added !",201));
    }
    //find all user bookings
    @GetMapping("/find/bookings")
    public ResponseEntity<List<Booking>> getBookingById(@AuthenticationPrincipal User user){
        List<Booking> bookings=bookingService.getBookingsByUserId(user.getId());
        return ResponseEntity.status(201).body(bookings);
    }
    //find booking of user of rest
    @GetMapping("/find/booking/{restID}")
    public ResponseEntity<List<Booking>> getBookingByRestId(@PathVariable Integer restID,@AuthenticationPrincipal User user){
        List<Booking> bookings=bookingService.getBookingByRestId(restID,user.getId());
        return ResponseEntity.status(201).body(bookings);
    }

    @PutMapping("/update/booking")
    public ResponseEntity updateBooking(@RequestBody Booking booking,@AuthenticationPrincipal User user) {
        boolean isValid=bookingService.updateBooking(booking,user.getId());
        if (!isValid)
            return ResponseEntity.status(400).body(new Api("Invalid UserID",400 ));
        else
            return ResponseEntity.status(200).body(new Api("Booking updated",200));
    }
    @DeleteMapping("/delete/booking/{id}")
    public ResponseEntity deleteBooking(@PathVariable Integer id,@AuthenticationPrincipal User user) {
        boolean isValid= bookingService.deleteBooking(id,user.getId());
        if(isValid){
            return ResponseEntity.status(200).body(new Api("Booking deleted !", 200));
        }else{
            return ResponseEntity.status(400).body(new Api("Invalid Booking id",400 ));}
    }

}
