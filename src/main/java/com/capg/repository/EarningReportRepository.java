package com.capg.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entity.EarningReport;

public interface EarningReportRepository extends JpaRepository<EarningReport, Integer> {
	Optional<EarningReport> findByBookingId(int bookingId);
	List<EarningReport> findByBookingDateBetween(LocalDate fromDate, LocalDate toDate);
}
