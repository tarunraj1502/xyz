package com.capg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.dto.ShowDto;
import com.capg.entity.Booking;
import com.capg.entity.Hall;
import com.capg.entity.HallNew;
import com.capg.entity.Movies;
import com.capg.entity.Shows;
import com.capg.exception.IdNotFoundException;
import com.capg.repository.HallNewRepository;
import com.capg.repository.HallRepository;
import com.capg.repository.MoviesRepository;
import com.capg.repository.ShowsRepository;
import com.capg.utility.AppConstant;

@Service
public class ShowsServiceImpl implements ShowsService{

	@Autowired
	private ShowsRepository showsRepository; 
	
	@Autowired
	private MoviesRepository moviesRepository;
	
	@Autowired
	private HallRepository hallRepository;
	
	@Autowired
	private HallNewRepository hallNewRepository;
	
	
	public List<Shows> getListOfShows() 
	{
		return showsRepository.findAll();
	}
	
	public Shows addShows(Shows show) {
		showsRepository.save(show);
		return show;
	}
	
	@Override
	 public String deleteShowsById(int id) {
		 String msg="";
			if(showsRepository.existsById(id))
			{
				showsRepository.deleteById(id);
				msg="Shows successfully deleted";
			}
			else
			{
				throw new IdNotFoundException(AppConstant.HALL_ID_NOT_FOUND_INFO);
			}
			
			return msg;
		}
	 
	 @Override
		public Shows updateShows(int id, Shows shows) {
			
		 Shows updatedShows=null;
			
			if(showsRepository.existsById(id))
			{
			   updatedShows =	showsRepository.save(shows);
			}
			else
			{
				throw new IdNotFoundException(AppConstant.SHOW_ID_NOT_FOUND_INFO);
			}
			
			return updatedShows;
		}
	@Override
	public Shows addShows2(ShowDto showDto) {
		
		Shows shows = new Shows();
		
		Movies movies = moviesRepository.findById(showDto.getMovieId()).get();
      //  Hall hall = hallRepository.findById(showDto.getHallId()).get();
	      HallNew hallNew = hallNewRepository.findById(showDto.getHallNewId()).get();
		
        shows.setFromDate(showDto.getFromDate());
        shows.setSlotNo(showDto.getSlotNo());
        shows.setToDate(showDto.getToDate());
        shows.setMovies(movies);
       // shows.setHall(hallNew);
        shows.setHallNew(hallNew);
		
        
        
		return showsRepository.save(shows);
	}


	
	public Shows getShowById(int showId) {
		Optional<Shows> showById=showsRepository.findById(showId);
		return showById.get();
	}
}
