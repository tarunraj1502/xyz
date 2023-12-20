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

import com.capg.dto.ShowDto;
import com.capg.entity.HallNew;
import com.capg.entity.Movies;
import com.capg.entity.Shows;
import com.capg.exception.IdNotFoundException;
import com.capg.repository.HallNewRepository;
import com.capg.repository.MoviesRepository;
import com.capg.repository.ShowsRepository;
import com.capg.service.ShowsServiceImpl;
import com.capg.utility.AppConstant;

@SpringBootTest
public class ShowsServiceImplTest {

    @Mock
    private ShowsRepository showsRepository;

    @Mock
    private MoviesRepository moviesRepository;

    @Mock
    private HallNewRepository hallNewRepository;

    @InjectMocks
    private ShowsServiceImpl showsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetListOfShows() {
        // Mocking the behavior of the repository
        List<Shows> showsList = new ArrayList<>();
        when(showsRepository.findAll()).thenReturn(showsList);

        // Performing the actual test
        List<Shows> result = showsService.getListOfShows();

        // Assertions
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testAddShows() {
        // Mocking the behavior of the repository
        Shows shows = new Shows(); // Initialize with required values
        when(showsRepository.save(any())).thenReturn(shows);

        // Performing the actual test
        Shows result = showsService.addShows(shows);

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }

    @Test
    public void testDeleteShowsById() {
        // Mocking the behavior of the repository
        int showId = 1; // Replace with the actual show ID
        when(showsRepository.existsById(showId)).thenReturn(true);

        // Performing the actual test
        String result = showsService.deleteShowsById(showId);

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }

    @Test
    public void testUpdateShows() {
        // Mocking the behavior of the repository
        int showId = 1; // Replace with the actual show ID
        Shows shows = new Shows(); // Initialize with required values
        when(showsRepository.existsById(showId)).thenReturn(true);
        when(showsRepository.save(any())).thenReturn(shows);

        // Performing the actual test
        Shows result = showsService.updateShows(showId, shows);

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }

    @Test
    public void testAddShows2() {
        // Mocking the behavior of the repository and other dependencies
        ShowDto showDto = new ShowDto(); // Initialize with required values
        Movies movies = new Movies(); // Initialize with required values
        HallNew hallNew = new HallNew(); // Initialize with required values
        Shows shows = new Shows(); // Initialize with required values

        when(moviesRepository.findById(anyInt())).thenReturn(Optional.of(movies));
        when(hallNewRepository.findById(anyInt())).thenReturn(Optional.of(hallNew));
        when(showsRepository.save(any())).thenReturn(shows);

        // Performing the actual test
        Shows result = showsService.addShows2(showDto);

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }

    @Test
    public void testGetShowById() {
        // Mocking the behavior of the repository
        int showId = 1; // Replace with the actual show ID
        Shows shows = new Shows(); // Initialize with required values
        when(showsRepository.findById(showId)).thenReturn(Optional.of(shows));

        // Performing the actual test
        Shows result = showsService.getShowById(showId);

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }

    // Add more test methods for other methods in ShowsServiceImpl

}
