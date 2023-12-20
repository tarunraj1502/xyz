package com.capg.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EarningReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	
	@Column(name = "booking_id")
	private int bookingId;
	
    @Column(name = "status")	
	private String status;
	
    @Column(name = "total_booking_cost")
	private double totalbookingCost;
	
	
    @Column(name = "seats_booked")
    private int seatsBooked;
    
    @Column(name = "booking_date")
	private LocalDate bookingDate;
    
    

	public EarningReport() {
	}

	public EarningReport(int bookingId, String status, double totalbookingCost, int seatsBooked,
			LocalDate bookingDate) {
		this.bookingId = bookingId;
		this.status = status;
		this.totalbookingCost = totalbookingCost;
		this.seatsBooked = seatsBooked;
		this.bookingDate = bookingDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalbookingCost() {
		return totalbookingCost;
	}

	public void setTotalbookingCost(double totalbookingCost) {
		this.totalbookingCost = totalbookingCost;
	}

	public int getSeatsBooked() {
		return seatsBooked;
	}

	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "EarningReport [id=" + id + ", bookingId=" + bookingId + ", status=" + status + ", totalbookingCost="
				+ totalbookingCost + ", seatsBooked=" + seatsBooked + ", bookingDate=" + bookingDate + "]";
	}
    
    
	
	
	

}

