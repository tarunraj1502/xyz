package com.capg.dto;

import java.time.LocalDate;
import java.util.List;

import com.capg.entity.Booking;
import com.capg.entity.Hall;
import com.capg.entity.Movies;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class ShowDto {

	private int slotNo;
	 
	 
	 private LocalDate fromDate;
	 
	 
	 private LocalDate toDate;
	 
	
	 private int movieId;
	 
	
//	 private int hallId;
	 private int hallNewId;


	public int getSlotNo() {
		return slotNo;
	}


	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}


	public LocalDate getFromDate() {
		return fromDate;
	}


	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}


	public LocalDate getToDate() {
		return toDate;
	}


	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}


	public int getMovieId() {
		return movieId;
	}


	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}


	

	public ShowDto(int slotNo, LocalDate fromDate, LocalDate toDate, int movieId, int hallNewId) {
		this.slotNo = slotNo;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.movieId = movieId;
		this.hallNewId = hallNewId;
	}


	public ShowDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getHallNewId() {
		return hallNewId;
	}


	public void setHallNewId(int hallNewId) {
		this.hallNewId = hallNewId;
	}
	 
	
	


}
