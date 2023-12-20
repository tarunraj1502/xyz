package com.capg.service;

import java.util.List;

import com.capg.entity.SeatType;
import com.capg.entity.SeatTypeNew;

public interface SeatTypeService {

	SeatType addSeatType(SeatType seatType);
	List<SeatType> getAllSeatType();
	String deleteSeatTypeById(int id);
	SeatTypeNew addNewSeatType(SeatTypeNew seatTypeNew);
}
