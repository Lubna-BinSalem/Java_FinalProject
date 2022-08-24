package com.example.bookatable.Service;

import com.example.bookatable.Model.Booking;
import com.example.bookatable.Model.User;
import com.example.bookatable.Repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public List<Booking> getAllBookings(User user) {
        return bookingRepository.findAll();
    }


    public void addBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByUserId(Integer id) {
        return bookingRepository.findBookingsByUserID(id);
    }
    public List<Booking> getBookingByRestId(Integer restID, Integer id) {
        return bookingRepository.findBookingsByUserIDAndRestID(id,restID);
    }

    public boolean updateBooking(Booking booking, Integer id) {
        Booking oldBooking=bookingRepository.findBookingById(booking.getId());
        if(oldBooking==null){
            return false;}
        oldBooking.setBookingDate(booking.getBookingDate());
        oldBooking.setNumOfPerson(booking.getNumOfPerson());
        bookingRepository.save(oldBooking);

        return true;
    }

    public boolean deleteBooking(Integer id, Integer id1) {
        Booking booking=bookingRepository.findBookingById(id);
        if(id1.equals(booking.getUserID()) ){
            bookingRepository.delete(booking);
            return true;
        }
        return false;
    }
}
