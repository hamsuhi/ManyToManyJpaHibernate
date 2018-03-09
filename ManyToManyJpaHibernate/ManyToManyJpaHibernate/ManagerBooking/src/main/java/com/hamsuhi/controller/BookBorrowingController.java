//package com.hamsuhi.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hamsuhi.entity.BookBorrowing;
//import com.hamsuhi.service.BookBorrowingService;
//
//@RestController
//@RequestMapping(value = "/api")
//public class BookBorrowingController {
//	
//	@Autowired
//	private BookBorrowingService bookBorrowingService;
//	
//	@RequestMapping(value = "/bookingBorrowing", method = RequestMethod.GET)
//	public ResponseEntity<List<BookBorrowing>> getListAll(){
//		List<BookBorrowing> list = bookBorrowingService.getAll();
//		if(list.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}else{
//			return new ResponseEntity<>(list,HttpStatus.OK);
//		}
//	}
//	
//	@RequestMapping(value = "/bookingBorrowing/{id}", method = RequestMethod.GET)
//    public ResponseEntity<?> getCourse(@PathVariable("id") int id) {
//		BookBorrowing bookBorrowing = bookBorrowingService.getById(id);
//        return new ResponseEntity<BookBorrowing>(bookBorrowing, HttpStatus.OK);
//    }
//
//}
