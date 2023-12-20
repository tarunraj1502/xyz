package com.capg;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

import com.capg.entity.SeatType;
import com.capg.entity.SeatTypeNew;
import com.capg.repository.SeatTypeNewRepository;
import com.capg.repository.SeatTypeRepository;
import com.capg.service.SeatTypeServiceImpl;

@SpringBootTest
public class SeatTypeServiceImplTest {

    @Mock
    private SeatTypeRepository seatTypeRepository;

    @Mock
    private SeatTypeNewRepository seatTypeNewRepository;

    @InjectMocks
    private SeatTypeServiceImpl seatTypeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddSeatType() {
        SeatType seatType = new SeatType();
        seatType.setSeatTypeDesc("Standard");

        when(seatTypeRepository.save(any())).thenReturn(seatType);

        SeatType result = seatTypeService.addSeatType(seatType);

        assertNotNull(result);
        assertEquals(seatType.getSeatTypeDesc(), result.getSeatTypeDesc());
        // Add more assertions based on your specific logic
    }

    @Test
    public void testGetAllSeatType() {
        List<SeatType> seatTypeList = new ArrayList<>();
        seatTypeList.add(new SeatType());
        seatTypeList.add(new SeatType());

        when(seatTypeRepository.findAll()).thenReturn(seatTypeList);

        List<SeatType> result = seatTypeService.getAllSeatType();

        assertNotNull(result);
        assertEquals(seatTypeList.size(), result.size());
    }

    @Test
    public void testDeleteSeatTypeById() {
        int seatTypeId = 1;

        when(seatTypeRepository.existsById(seatTypeId)).thenReturn(true);
        doNothing().when(seatTypeRepository).deleteById(seatTypeId);

        String result = seatTypeService.deleteSeatTypeById(seatTypeId);

        assertEquals("Hall successfully deleted", result);
    }

    @Test
    public void testDeleteSeatTypeByIdWithInvalidId() {
        int seatTypeId = 1;

        when(seatTypeRepository.existsById(seatTypeId)).thenReturn(false);

        String result = seatTypeService.deleteSeatTypeById(seatTypeId);

        assertEquals("", result);
    }

    @Test
    public void testGetSeatTypeById() {
        int seatTypeId = 1;
        SeatType seatType = new SeatType();
        seatType.setSeatTypeDesc("Standard");

        Optional<SeatType> optionalSeatType = Optional.of(seatType);

        when(seatTypeRepository.findById(seatTypeId)).thenReturn(optionalSeatType);

        SeatType result = seatTypeService.getSeatTypeById(seatTypeId);

        assertNotNull(result);
        assertEquals(seatType.getSeatTypeDesc(), result.getSeatTypeDesc());
    }

    @Test
    public void testAddNewSeatType() {
        SeatTypeNew seatTypeNew = new SeatTypeNew();
        seatTypeNew.setSeatTypeName("VIP");

        when(seatTypeNewRepository.save(any())).thenReturn(seatTypeNew);

        SeatTypeNew result = seatTypeService.addNewSeatType(seatTypeNew);

        assertNotNull(result);
        assertEquals(seatTypeNew.getSeatTypeDescription(), result.getSeatTypeDescription());
    }

    // Add more test methods for other scenarios and methods in SeatTypeServiceImpl

}
