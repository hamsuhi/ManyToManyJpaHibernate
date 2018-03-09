package com.hamsuhi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hamsuhi.entity.BookBorrowing;
import com.hamsuhi.entity.BookBorrowingId;
import com.hamsuhi.entity.Booking;
import com.hamsuhi.entity.Reader;
import com.hamsuhi.repository.BookBorrowingRepository;
import com.hamsuhi.service.BookBorrowingService;
import com.hamsuhi.service.BookingService;
import com.hamsuhi.service.ReaderService;

@RestController
@RequestMapping(value = "/api")
public class BookBorrowingController {
	SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
	@Autowired
	private BookBorrowingService bookBorrowingService;

	@Autowired
	private BookBorrowingRepository bookBorrowingRepository;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private ReaderService readerService;

	@RequestMapping(value = "/bookingBorrowing", method = RequestMethod.GET)
	public ResponseEntity<List<BookBorrowing>> getListAll() {
		List<BookBorrowing> list = bookBorrowingService.getAll();
		if (list.isEmpty()) {
			return new ResponseEntity<List<BookBorrowing>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<BookBorrowing>>(list, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/bookingBorrowing/{numberCard}/{bookingId}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable("numberCard") int readId, @PathVariable("bookingId") int bookId) {
		BookBorrowing booking = bookBorrowingService.getById(readId, bookId);
		if (booking != null) {
			return new ResponseEntity<BookBorrowing>(booking, HttpStatus.OK);
		}
		return new ResponseEntity<BookBorrowing>(booking, HttpStatus.NOT_FOUND);
	}

	// truyen vao x-www-form
	@PostMapping(value = "/bookingBorrowing")
	public ResponseEntity<?> addUseRaw(String numberCard, String bookingId, String dateBorrowing, String datePay,
			UriComponentsBuilder ucBuilder) throws ParseException {

		BookBorrowingId bookReadId = new BookBorrowingId(Integer.parseInt(numberCard), Integer.parseInt(bookingId));
		BookBorrowing bookReadNew = new BookBorrowing(bookReadId, date.parse(dateBorrowing), date.parse(datePay));
		boolean test = bookBorrowingService.add(bookReadNew);
		if (test == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/booking/{id}").buildAndExpand(bookReadNew.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// truyen vao x-www-form
	@PutMapping(value = "/bookingBorrowing/{id}")
	public ResponseEntity<?> updateUseRaw(@PathVariable("id") int id, @RequestParam("numberCard") String numberCard,
			@RequestParam("bookingId") String bookingId, @RequestParam("dateBorrowing") String dateBorrowing,
			@RequestParam("datePay") String datePay, @RequestParam("booking") int booking,
			@RequestParam("reader") int reader) throws ParseException {

		Booking booking2 = bookingService.getByIdBooking(booking);
		Reader reader2 = readerService.getByIdReader(reader);
		BookBorrowingId bookReadId = new BookBorrowingId(Integer.parseInt(numberCard), Integer.parseInt(bookingId));
		BookBorrowing bookReadNew = new BookBorrowing(bookReadId, booking2, reader2, date.parse(dateBorrowing),
				date.parse(datePay));
		boolean test = bookBorrowingService.update(bookReadNew);
		if (test == false) {
			return new ResponseEntity<BookBorrowing>(bookReadNew, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BookBorrowing>(bookReadNew, HttpStatus.OK);
	}

	@RequestMapping(value = "/bookingBorrowing/{id}/{bookingId}/{numberCard}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable("numberCard") int readId, @PathVariable("bookingId") int bookId) {

		if (bookBorrowingService.deleteById(readId, bookId) == false) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

}
