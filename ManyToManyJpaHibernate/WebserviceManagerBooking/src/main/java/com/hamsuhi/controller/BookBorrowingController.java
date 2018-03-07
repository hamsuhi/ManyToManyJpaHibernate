package com.hamsuhi.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hamsuhi.entity.BookBorrowing;
import com.hamsuhi.service.BookBorrowingService;

@RestController
@RequestMapping(value = "/api")
public class BookBorrowingController {

	@Autowired
	private BookBorrowingService bookBorrowingService;

	@RequestMapping(value = "/bookingBorrowing", method = RequestMethod.GET)
	public ResponseEntity<List<BookBorrowing>> getListAll() {
		List<BookBorrowing> list = bookBorrowingService.getAll();
		if (list.isEmpty()) {
			return new ResponseEntity<List<BookBorrowing>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<BookBorrowing>>(list, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/bookingBorrowing/{bookingId}/{numberCard}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable("bookingId") Integer bookId,
			@PathVariable(" numberCard ") Integer readId) {
		BookBorrowing booking = bookBorrowingService.getById(readId, bookId);
		if (booking != null) {
			return new ResponseEntity<BookBorrowing>(booking, HttpStatus.OK);
		}
		return new ResponseEntity<BookBorrowing>(booking, HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/bookingBorrowing/")
	public ResponseEntity<BookBorrowing> addUseRaw(@RequestBody BookBorrowing bookBorrowing,
			UriComponentsBuilder ucBuilder) {
		BookBorrowing test = bookBorrowingService.add(bookBorrowing);
		if (test != null) {
			return new ResponseEntity<BookBorrowing>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/booking/{id}").buildAndExpand(bookBorrowing.getBooking()).toUri());
		return new ResponseEntity<BookBorrowing>(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/bookingBorrowing/")
	public ResponseEntity<BookBorrowing> updateUseRaw(@RequestBody BookBorrowing bookBorrowing) {
		bookBorrowingService.update(bookBorrowing);
		return new ResponseEntity<BookBorrowing>(bookBorrowing, HttpStatus.OK);
	}

	@RequestMapping(value = "/bookingBorrowing/{bookingId}/{numberCard}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable("bookingId") Integer bookId,
			@PathVariable(" numberCard ") Integer readId) {
		if (bookBorrowingService.deleteById(readId, bookId) == false) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

}
