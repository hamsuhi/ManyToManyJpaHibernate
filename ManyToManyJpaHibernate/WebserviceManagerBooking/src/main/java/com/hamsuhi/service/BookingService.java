package com.hamsuhi.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hamsuhi.entity.Booking;
import com.hamsuhi.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	public List<Booking> getAllBooking() {
		List<Booking> list = bookingRepository.findAll();
		if (list != null) {
			return list;
		}
		return null;
	}

	public Booking getByIdBooking(int id) {
		Booking bookNew = bookingRepository.findOne(id);
		if (bookNew != null) {
			return bookingRepository.findByBookingId(id);
		}
		return null;
	}

	public Booking addBooking(Booking booking) {
		Booking bookNew = bookingRepository.findOne(booking.getBookingId());
		if (bookNew == null) {
			bookingRepository.save(booking);
			System.out.println("add thanh cong booking");
			return booking;
		}
		return null;
	}

	public boolean updateBooking(Booking booking) {
		Booking bookNew = bookingRepository.findOne(booking.getBookingId());
		if (bookNew != null) {
			bookingRepository.saveAndFlush(booking);
			System.out.println("update ok");
			return true;
		}
		System.out.println("update not nnot ok");
		return false;
	}

	public boolean deleteByIdBooking(int id) {
		if (this.getByIdBooking(id) == null) {
			return false;
		} else {
			bookingRepository.delete(id);
			return true;
		}
	}

}
