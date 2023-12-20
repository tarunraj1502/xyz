package com.capg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.capg.entity.Hall;
import com.capg.entity.Movies;
import com.capg.entity.Shows;
import com.capg.repository.ShowsRepository;
import com.capg.service.ShowsServiceImpl;

public class ShowServiceTests {
	
	@Mock
	private ShowsRepository showsRepository;
	
	@InjectMocks
	private ShowsServiceImpl showServiceImpl;
	
	@Test
	public void addShowTest()
	{
//        Movies movies=new Movies(1,"pathaan","Action");
//  		Hall hall=new Hall();	
//		LocalDate fromDate=LocalDate.parse("2023-12-14");
//		 Shows show=new Shows(1, 4, fromDate, fromDate, movies, hall, null);
//		when(showsRepository.save(show)).thenReturn(show);
//		assertEquals(show, showServiceImpl.addShows(show));
		
		
	//LocalDate toDate=	LocalDate.of(2023, 12, 23);
	}

}
