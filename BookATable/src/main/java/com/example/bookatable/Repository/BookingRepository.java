package com.example.bookatable.Repository;

import com.example.bookatable.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findBookingsByUserID(Integer userID);
    List<Booking> findBookingsByUserIDAndRestID(Integer id, Integer restID);
    Booking findBookingById(Integer id);

}
