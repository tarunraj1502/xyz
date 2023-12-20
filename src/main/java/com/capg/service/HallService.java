package com.capg.service;

import java.util.List;

import com.capg.entity.Hall;
import com.capg.entity.HallNew;

public interface HallService {

	Hall addHall(Hall hall);
	List<Hall> getAllHall();
	String deleteHallById(int id);
	Hall updateHall(int id, Hall hall);
	Hall getHallById(int id);
	HallNew addNewHall(HallNew hallNew);
//	Hall update
	
}
