//package com.hamsuhi.controller;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.hamsuhi.entity.BookBorrowing;
//import com.hamsuhi.entity.BookBorrowingId;
//import com.hamsuhi.entity.Booking;
//import com.hamsuhi.entity.Reader;
//import com.hamsuhi.repository.BookBorrowingRepository;
//import com.hamsuhi.service.BookBorrowingService;
//import com.hamsuhi.service.BookingService;
//import com.hamsuhi.service.ReaderService;
//
//@Component
//public class Controller implements CommandLineRunner {
//
//	@Autowired
//	BookingService bookingService;
//
//	@Autowired
//	ReaderService readerService;
//
//	@Autowired
//	BookBorrowingService bookBorrowingService;
//
//	@Autowired
//	BookBorrowingRepository bookBorrowingRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Booking b = new Booking();
//		b.setBookingId(1);
//		b.setBookName("book amma");
//		b.setPublisher("publiis");
//		 bookingService.addBooking(b);
//		//bookingService.updateBooking(b);
//
//		Booking b1 = new Booking();
//		b1.setBookingId(2);
//		b1.setBookName("tabasan");
//		b1.setPublisher("publiissdf");
//		bookingService.addBooking(b1);
//		//bookingService.updateBooking(b1);
//
//		// bookingService.getByIdBooking(1);
//		// System.out.println("layu ra" );
//		// bookingService.deleteByIdBooking(4);
//		// bookingService.getAllBooking();
//
//		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
//		Reader reader1 = new Reader();
//		reader1.setNumberCard(3);
//		reader1.setDateRange(d.parse("09/02/2017"));
//		reader1.setReaderName("huong max");
//		reader1.setTel("1300");
//		readerService.addReader(reader1);
//		//readerService.updateReader(reader1);
//
//		BookBorrowingId bid = new BookBorrowingId(reader1.getNumberCard(), b.getBookingId());
//		BookBorrowing bb = new BookBorrowing(bid, b, reader1, d.parse("20/09/2017"), d.parse("12/09/2018"));
//		//bookBorrowingRepository.save(bb);
//		bookBorrowingService.update(bb);
//		bookBorrowingService.getById(bid.getNumberCard(), bid.getBookingId());
//		bookBorrowingService.deleteById(bid.getNumberCard(), bid.getBookingId());
//		
////		BookBorrowingId bid1 = new BookBorrowingId(reader1.getNumberCard(), b1.getBookingId());
////		BookBorrowing bb1 = new BookBorrowing(bid1, b, reader1, d.parse("13/09/2017"), d.parse("12/09/2018"));
////		bookBorrowingRepository.save(bb1);
//		//bookBorrowingRepository.saveAndFlush(bb);
//		
////		if(bookBorrowingRepository.findByBookingBookNameContaining("book amma") != null) {
////		System.out.println("'Tim thay");
////		}
////		if(bookBorrowingRepository.findByIdNumberCard(3) != null) {
////			System.out.println("'Tim thay");
////		}
//		
////		bookBorrowingRepository.delete(bid);
//		
//		//bookBorrowingRepository.delete(bb);
//		// readerService.updateReader(1, reader1);
//
//		// readerService.getAllReading();
//
//		// readerService.deleteByIdReader(2);
//
//		// bookBorrowingService.getAll();
//		// bookBorrowingService.getById(1, 1);
//		// BookBorrowingId ba = new BookBorrowingId(1, 1);
//		// BookBorrowing bq = new BookBorrowing( b, reader1, d.parse("09/02/2017"),
//		// d.parse("09/02/2017"));
//		// bookBorrowingService.add(bq);
//
//	}
//
//}
