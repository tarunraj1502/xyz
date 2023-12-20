package com.capg;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.entity.Hall;
import com.capg.entity.HallNew;
import com.capg.exception.IdNotFoundException;
import com.capg.repository.HallNewRepository;
import com.capg.repository.HallRepository;
import com.capg.repository.SeatTypeRepository;
import com.capg.service.HallServiceImpl;
import com.capg.utility.AppConstant;

@SpringBootTest
public class HallServiceImplTest {

    @Mock
    private HallRepository hallRepository;

    @Mock
    private SeatTypeRepository seatTypeRepository;

    @Mock
    private HallNewRepository hallNewRepository;

    @InjectMocks
    private HallServiceImpl hallService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddHall() {
        // Mocking the behavior of the repository
        Hall hall = new Hall(); // Initialize with required values
        when(hallRepository.save(any())).thenReturn(hall);

        // Performing the actual test
        Hall result = hallService.addHall(hall);

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }

    @Test
    public void testGetAllHall() {
        // Mocking the behavior of the repository
        List<Hall> hallList = new ArrayList<>();
        when(hallRepository.findAll()).thenReturn(hallList);

        // Performing the actual test
        List<Hall> result = hallService.getAllHall();

        // Assertions
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testDeleteHallById() {
        // Mocking the behavior of the repository
        int hallId = 1; // Replace with the actual hall ID
        when(hallRepository.existsById(hallId)).thenReturn(true);

        // Performing the actual test
        String result = hallService.deleteHallById(hallId);

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }

    @Test
    public void testUpdateHall() {
        // Mocking the behavior of the repository
        int hallId = 1; // Replace with the actual hall ID
        Hall hall = new Hall(); // Initialize with required values
        when(hallRepository.existsById(hallId)).thenReturn(true);
        when(hallRepository.save(any())).thenReturn(hall);

        // Performing the actual test
        Hall result = hallService.updateHall(hallId, hall);

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }

    @Test
    public void testGetHallById() {
        // Mocking the behavior of the repository
        int hallId = 1; // Replace with the actual hall ID
        Hall hall = new Hall(); // Initialize with required values
        when(hallRepository.findById(hallId)).thenReturn(Optional.of(hall));

        // Performing the actual test
        Hall result = hallService.getHallById(hallId);

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }

    @Test
    public void testAddNewHall() {
        // Mocking the behavior of the repository
        HallNew hallNew = new HallNew(); // Initialize with required values
        when(hallNewRepository.save(any())).thenReturn(hallNew);

        // Performing the actual test
        HallNew result = hallService.addNewHall(hallNew);

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }

    // Add more test methods for other methods in HallServiceImpl

}
