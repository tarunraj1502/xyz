package com.capg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dto.ShowDto;
import com.capg.entity.Hall;
import com.capg.entity.Shows;
import com.capg.repository.ShowsRepository;
import com.capg.service.ShowsService;
import com.capg.service.ShowsServiceImpl;
@RestController
@RequestMapping("/api/v1")
public class ShowsController {

	

		@Autowired
		private ShowsServiceImpl showsService;
//		@Autowired
//		private ShowService2 showservice2;
	//	
		@GetMapping("user/getShow/{showId}")
	    public ResponseEntity<Shows> getShowById (@PathVariable int showId)
	    {
	    	return new ResponseEntity<Shows> (showsService.getShowById(showId),HttpStatus.OK);
	    }
		
	 
		
		@GetMapping("user/listAllShows")
		public ResponseEntity<List<Shows>> getListOfShows()
		{
			return new ResponseEntity<List<Shows>> (showsService.getListOfShows(),HttpStatus.OK);
		}
		@PostMapping("admin/addShows")
		public ResponseEntity<Shows> addShows(@RequestBody ShowDto showDto)
		{
			return new ResponseEntity<Shows>(showsService.addShows2(showDto),HttpStatus.OK);
		}
	 
		@PutMapping("admin/shows/{showId}")
		public ResponseEntity<Shows> updateShows(@PathVariable int showId,@RequestBody Shows show)
		{
			System.out.println(show);
			return new ResponseEntity<Shows>(showsService.updateShows(showId,show),HttpStatus.OK);
		}
		@DeleteMapping("admin/shows/{showId}")
		public ResponseEntity<String> deleteShows(@PathVariable int showId)
		{
			return new ResponseEntity<String>(showsService.deleteShowsById(showId),HttpStatus.OK);
		}
		/*@PostMapping("/addShows2")
		public Shows addShows2(@RequestBody ShowDto showDto)
		{
		      return showservice2.addShow(showDto);
		}*/
	}


	
	

