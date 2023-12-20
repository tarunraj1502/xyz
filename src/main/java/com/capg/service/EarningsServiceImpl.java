package com.capg.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.entity.EarningReport;
import com.capg.repository.EarningReportRepository;
import com.capg.dto.EarningsInputDto;
import com.capg.dto.EarningsOutputDto;
import com.capg.entity.EarningReport;
import com.capg.repository.EarningReportRepository;

@Service
public class EarningsServiceImpl implements EarningsService {
	@Autowired
	private EarningReportRepository earningsRepository;

	public EarningsOutputDto generateEarningsReport(EarningsInputDto earningsInputDto) {
		List<EarningReport> earningsList = earningsRepository.findByBookingDateBetween(earningsInputDto.getFromDate(),
				earningsInputDto.getToDate());
		Map<LocalDate, Integer> seatsBookedPerDay = new HashMap<>();
		Map<LocalDate, Double> totalEarningsPerDay = new HashMap<>();
		Map<LocalDate, Double> cancellationChargesPerDay = new HashMap<>();
		for (EarningReport earning : earningsList) {
			seatsBookedPerDay.put(earning.getBookingDate(),
					seatsBookedPerDay.getOrDefault(earning.getBookingDate(), 0) + earning.getSeatsBooked());
			if (earning.getStatus().equals("booked"))
				totalEarningsPerDay.put(earning.getBookingDate(),
						totalEarningsPerDay.getOrDefault(earning.getBookingDate(), 0.0)
								+ earning.getTotalbookingCost());
			if (earning.getStatus().equals("cancelled"))
				cancellationChargesPerDay.put(earning.getBookingDate(),
						cancellationChargesPerDay.getOrDefault(earning.getBookingDate(), 0.0)
								+ earning.getTotalbookingCost() * 0.2);
		}
		double totalBookingCharges = totalEarningsPerDay.values().stream().mapToDouble(a -> a).sum();
		double totalCancellationCharges = cancellationChargesPerDay.values().stream().mapToDouble(a -> a).sum();
		double totalEarnings = totalBookingCharges + totalCancellationCharges;
		EarningsOutputDto earningsOutputDto = new EarningsOutputDto();
		earningsOutputDto.setSeatsBookedPerDay(seatsBookedPerDay);
		earningsOutputDto.setTotalEarningsPerDay(totalEarningsPerDay);
		earningsOutputDto.setCancellationChargesPerDay(cancellationChargesPerDay);
		earningsOutputDto.setTotalBookingCharges(totalBookingCharges);
		earningsOutputDto.setTotalCancellationCharges(totalCancellationCharges);
		earningsOutputDto.setTotalEarnings(totalEarnings);
		System.out.println(earningsOutputDto);
		return earningsOutputDto;
	}
}