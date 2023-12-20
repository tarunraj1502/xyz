package com.capg;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.dto.BookingNewDto;
import com.capg.entity.Booking;
import com.capg.entity.SeatType;
import com.capg.entity.SeatTypeNew;
import com.capg.entity.Shows;
import com.capg.entity.Users;
import com.capg.exception.IdNotFoundException;
import com.capg.exception.SeatNotAvailable;
import com.capg.exception.ShowDateNotFound;
import com.capg.repository.BookingNewRepository;
import com.capg.repository.BookingRepository;
import com.capg.repository.SeatTypeNewRepository;
import com.capg.repository.SeatTypeRepository;
import com.capg.repository.ShowsRepository;
import com.capg.repository.UsersRepository;
import com.capg.service.BookingServiceImpl;

@SpringBootTest
public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ShowsRepository showsRepository;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private SeatTypeRepository seatTypeRepository;

    @Mock
    private SeatTypeNewRepository seatTypeNewRepository;

    @Mock
    private BookingNewRepository bookingNewRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooking() {
        // Mocking the behavior of the repository
        List<Booking> bookingList = new ArrayList<>();
        when(bookingRepository.findAll()).thenReturn(bookingList);

        // Performing the actual test
        List<Booking> result = bookingService.getAllBooking();

        // Assertions
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testBookingSeats() {
        // Mocking the behavior of the repositories and entities
        Users user = new Users(); // Initialize with required values
        Shows show = new Shows(); // Initialize with required values
        SeatType seatType = new SeatType(); // Initialize with required values
        List<SeatType> seatTypeList = new ArrayList<>();
        seatTypeList.add(seatType);

        Booking booking = new Booking();
        booking.setUsers(user);
        booking.setShows(show);
        booking.setSeatType(seatTypeList);
        booking.setNoOfSeats(2);
        booking.setShowDate(LocalDate.now().plusDays(2)); // Future date

        when(usersRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(showsRepository.findById(anyInt())).thenReturn(Optional.of(show));
        when(seatTypeRepository.findById(anyInt())).thenReturn(Optional.of(seatType));
        when(bookingNewRepository.save(any())).thenReturn(booking);

        // Performing the actual test
        Booking result = bookingService.bookingSeats(booking);

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }

    // Add more test methods for other methods in BookingServiceImpl

}

