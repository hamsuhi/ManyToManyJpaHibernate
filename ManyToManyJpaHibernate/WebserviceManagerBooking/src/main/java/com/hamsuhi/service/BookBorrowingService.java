package com.hamsuhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hamsuhi.entity.BookBorrowing;
import com.hamsuhi.entity.BookBorrowingId;
import com.hamsuhi.entity.Reader;
import com.hamsuhi.repository.BookBorrowingRepository;
//can sua add va update
@Service
public class BookBorrowingService {

	@Autowired
	private BookBorrowingRepository bookBorrowingRepository;

	public List<BookBorrowing> getAll() {
		List<BookBorrowing> list = bookBorrowingRepository.findAll();
		if (list != null) {
			return list;
		}
		return null;
	}

	@SuppressWarnings("unused")
	public BookBorrowing getById(Integer numberCard, Integer bookingId) {
		BookBorrowingId bookNew = new BookBorrowingId(numberCard, bookingId);
		if (bookNew != null) {
			System.out.println("get by id book borrowing" + bookNew.getNumberCard());
			return bookBorrowingRepository.findOne(bookNew);
		}
		return null;
	}

	//Khi add can them ca id khi tao data first
	public BookBorrowing add(BookBorrowing bookBorrowing) {
		BookBorrowingId bookBorrowingID = new BookBorrowingId(bookBorrowing.getId().getBookingId(),
				bookBorrowing.getId().getNumberCard());

		if (bookBorrowingRepository.findOne(bookBorrowingID) != null) {
			return null;
		} else {
			System.out.println("add thanh cong");
			return bookBorrowingRepository.save(bookBorrowing);
		}
	}

	public boolean update(BookBorrowing bookBorrowing) {
		BookBorrowingId bookBorrowingId = new BookBorrowingId(bookBorrowing.getId().getBookingId(),
				bookBorrowing.getId().getNumberCard());

		if (bookBorrowingRepository.findOne(bookBorrowingId) != null) {
			return false;
		}
		bookBorrowingRepository.saveAndFlush(bookBorrowing);
		System.out.println("update thanh cong");
		return true;

	}

	//de xoa  duoc can xoa bang cha (chua PK) truoc.
	public boolean deleteById(Integer numberCard, Integer bookingId) {
		BookBorrowingId bookBorrowingId = new BookBorrowingId(numberCard, bookingId);
		BookBorrowing bookBorrowing = bookBorrowingRepository.getOne(bookBorrowingId);
		if (bookBorrowing != null) {
			bookBorrowingRepository.delete(bookBorrowingId);
			System.out.println("Xoa thanh cong");
			return true;
		}
		return false;
	}

}
