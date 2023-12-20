package com.capg.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.dto.BookingNewDto;
import com.capg.entity.Booking;
import com.capg.entity.BookingNew;
import com.capg.entity.EarningReport;
import com.capg.entity.HallNew;
import com.capg.entity.SeatType;
import com.capg.entity.SeatTypeNew;
import com.capg.entity.Shows;
import com.capg.entity.Users;
import com.capg.exception.IdNotFoundException;
import com.capg.exception.SeatNotAvailable;
import com.capg.exception.ShowDateNotFound;
import com.capg.repository.BookingNewRepository;
import com.capg.repository.BookingRepository;
import com.capg.repository.EarningReportRepository;
import com.capg.repository.HallNewRepository;
import com.capg.repository.HallRepository;
import com.capg.repository.SeatTypeNewRepository;
import com.capg.repository.SeatTypeRepository;
import com.capg.repository.ShowsRepository;
import com.capg.repository.UsersRepository;
import com.capg.utility.AppConstant;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private ShowsRepository showsRepository;

	@Autowired
	private UsersRepository usersRepository;
//	private Object shows;

	@Autowired
	private HallRepository hallRepository;

	@Autowired
	private SeatTypeRepository seatTypeRepository;

	@Autowired
	private SeatTypeNewRepository seatTypeNewRepository;

	@Autowired
	private BookingNewRepository bookingNewRepository;

	@Autowired
	private HallNewRepository hallNewRepository;
	
	@Autowired
	private EarningReportRepository earningReportRepository;

	public List<Booking> getAllBooking() {
		return bookingRepository.findAll();
	}

	public Booking bookingSeats(Booking booking) {
		
		booking.setBookedDate(LocalDate.now());
		
		usersRepository.findById(booking.getUsers().getUserId()).orElseThrow(
				() -> new IdNotFoundException("USER NOT FOUND  WITH ID: " + booking.getUsers().getUserId()));

		Shows show = showsRepository.findById(booking.getShows().getShowId()).orElseThrow(
				() -> new IdNotFoundException("SHOW NOT FOUND WITH ID: " + booking.getShows().getShowId()));
		List<SeatType> seatTypeOptional = booking.getSeatType();
		LocalDate fromDate1 = show.getFromDate();
		LocalDate toDate1 = show.getToDate();
		SeatType seatType = null;
		if (booking.getShowDate().isBefore(booking.getBookedDate())) {
			throw new ShowDateNotFound(AppConstant.BEFORE_BOOKING_DATE_INFO);
		}

		else if (!booking.getShowDate().minusDays(1).isBefore(toDate1)
				&& booking.getShowDate().plusDays(1).isAfter(fromDate1)) {
			throw new ShowDateNotFound(AppConstant.SHOW_NOT_IN_RANGE_INFO);
		}

		else {
			for (SeatType seatTemp : seatTypeOptional) {
				seatTypeRepository.findById(seatTemp.getSeatTypeId())
						.orElseThrow(() -> new IdNotFoundException("SEAT TYPE  NOT FOUND WITH ID"));
			}

			for (SeatType seatTemp : seatTypeOptional) {
				seatType = seatTypeRepository.findById(seatTemp.getSeatTypeId()).get();
				int availableSeat = seatType.getRemainingSeat();
				if (availableSeat >= booking.getNoOfSeats()) {
					seatType.setRemainingSeat(availableSeat - booking.getNoOfSeats());
				} else {
					throw new SeatNotAvailable(AppConstant.SEAT_NOT_AVAILABLE_INFO);
				}
			}
			bookingRepository.save(booking);
			seatType.setBookings(booking);
			seatTypeRepository.save(seatType);
		}
		double totalCost = seatType.getSeatFare() * booking.getNoOfSeats();
		return booking;
	}

	@Override
	public String deleteBookingById(int id) {
		String msg = "";
		if (bookingRepository.existsById(id)) {
			bookingRepository.deleteById(id);
			msg = "Booking successfully deleted";
		}

		else {
			throw new IdNotFoundException(AppConstant.BOOKING_ID_NOT_FOUND_INFO);
		}

		return msg;
	}

	@Override
	public Booking updateBooking(int id, Booking booking) {

		if (bookingRepository.existsById(id))

		{
			booking.setBookingId(id);
		}

		else {
			throw new IdNotFoundException(AppConstant.BOOKING_ID_NOT_FOUND_INFO);
		}

		return bookingRepository.save(booking);
	}

	public Booking getBookingById(int bookingId) {
		Optional<Booking> getBookingById = bookingRepository.findById(bookingId);
		if (getBookingById.isEmpty()) {
			throw new IdNotFoundException(AppConstant.BOOKING_ID_NOT_FOUND_INFO);
		} else {
			return getBookingById.get();
		}
	}

	public String cancelBooking(int bookingId) {

		return null;
	}

	public BookingNew createNewBooking(BookingNewDto bookingNewDto) {
		System.out.println(bookingNewDto);
		Users user = usersRepository.findById(bookingNewDto.getUserId())
				.orElseThrow(() -> new IdNotFoundException("USER NOT FOUND  WITH ID: " + bookingNewDto.getUserId()));

		Shows show = showsRepository.findById(bookingNewDto.getShowId())
				.orElseThrow(() -> new IdNotFoundException("SHOW NOT FOUND WITH ID: " + bookingNewDto.getShowId()));
		System.out.println(show);
		SeatTypeNew seatTypeNew = seatTypeNewRepository.findById(bookingNewDto.getSeatTypeId()).get();
		if (seatTypeNew == null) {
			throw new IdNotFoundException("SEAT TYPE  NOT FOUND WITH ID");
		}
		LocalDate fromDate1 = show.getFromDate();
		LocalDate toDate1 = show.getToDate();

		if (bookingNewDto.getShowDate().isBefore(LocalDate.now())) {
			throw new ShowDateNotFound(AppConstant.BEFORE_BOOKING_DATE_INFO);
		} else if (!bookingNewDto.getShowDate().minusDays(1).isBefore(toDate1)
				&& bookingNewDto.getShowDate().plusDays(1).isAfter(fromDate1)) {
			throw new ShowDateNotFound(AppConstant.SHOW_NOT_IN_RANGE_INFO);
		} else {
			BookingNew bookingNew = new BookingNew();
			bookingNew.setUserId(bookingNewDto.getUserId());
			bookingNew.setUserName(user.getUserName());
			bookingNew.setMovieName(show.getMovies().getMovieName());
			bookingNew.setBookedDate(LocalDate.now());
			bookingNew.setShowDate(bookingNewDto.getShowDate());
			bookingNew.setSeatDescription(seatTypeNew.getSeatTypeDescription());
			bookingNew.setSeatFare(seatTypeNew.getSeatFare());
			bookingNew.setNoOfSeats(bookingNewDto.getNoOfSeats());
			bookingNew.setBookingAmount(bookingNewDto.getNoOfSeats() * seatTypeNew.getSeatFare());
			bookingNew.setShowId(show.getShowId());
			HallNew hallNew = hallNewRepository.findById(show.getHallNew().getHallId()).get();
			if (hallNew == null) {
				throw new IdNotFoundException(AppConstant.HALL_ID_NOT_FOUND_INFO);
			}
			hallNew.setRemainingSeats(hallNew.getRemainingSeats() - bookingNewDto.getNoOfSeats());
			hallNewRepository.save(hallNew);
			
			bookingNew = bookingNewRepository.save(bookingNew);
			earningReportRepository.save(new EarningReport(bookingNew.getBookingId(), "booked", bookingNew.getBookingAmount(), bookingNew.getNoOfSeats(), bookingNew.getBookedDate()));
			return bookingNew;
			//			return null;
		}
	}

	public String cancelBookingNew(int bookingId) {
		LocalDate currentDate = LocalDate.now();
		Optional<BookingNew> opBookingNew = bookingNewRepository.findById(bookingId);
		
		if(opBookingNew.isEmpty()) throw new RuntimeException("bookingid not found");
		
		BookingNew bookingNew = opBookingNew.get();
		if (currentDate.until(bookingNew.getShowDate()).getDays() > 2) {
			int seats = bookingNew.getNoOfSeats();
			bookingNew.setNoOfSeats(0);
			Optional<Shows> opShow = showsRepository.findById(bookingNew.getShowId());
			if(opShow.isEmpty()) throw new ShowDateNotFound("not foudn");
			Shows show = opShow.get();
			HallNew hall = hallNewRepository.findById(show.getHallNew().getHallId()).get();
			int remainingSeats = hall.getRemainingSeats();
			hall.setRemainingSeats(remainingSeats + seats);
			bookingNew.setBookingOrderStatus("Cancelled");
			hallNewRepository.save(hall);
			bookingNewRepository.save(bookingNew);
			
			
			Optional<EarningReport> opEarningReport = earningReportRepository.findByBookingId(bookingId);
			if(opEarningReport.isEmpty()) throw new RuntimeException("exception");
			
			EarningReport earningReport = opEarningReport.get();
			earningReport.setStatus("cancelled");
			earningReportRepository.save(earningReport);
			return "Booking Cancellation Successfull";
		} else {
			System.out.println("Exception code");
			return "Exception Code";
		}
	}

}