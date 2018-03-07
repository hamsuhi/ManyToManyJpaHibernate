package com.hamsuhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hamsuhi.entity.Booking;
import com.hamsuhi.entity.Reader;
import com.hamsuhi.entity.Reader;
import com.hamsuhi.repository.ReaderRepository;

@Service
public class ReaderService {

	@Autowired
	private ReaderRepository readerRepository;

	public List<Reader> getAllReading() {
		List<Reader> list = readerRepository.findAll();
		if (list != null) {
			return list;
		}
		return null;
	}

	public Reader getByIdReader(int id) {
		Reader readeNew = readerRepository.getOne(id);
		if (readeNew != null) {
			return readeNew;
		}
		return null;
	}

	public Reader addReader(Reader reader) {
		Reader readeNew = readerRepository.findOne(reader.getNumberCard());
		if (readeNew == null) {
			readerRepository.save(reader);
			System.out.println("add thanh cong reader");
			return reader;
		}
		return null;
	}

	public boolean updateReader( Reader reader) {
			Reader readeNew = readerRepository.findOne(reader.getNumberCard());
			if (readeNew != null) {
				readerRepository.saveAndFlush(reader);
				System.out.println("update ok");
				return true;
			}
			System.out.println("update not nnot ok");
		return false;
	}

	public boolean deleteByIdReader(int id) {
		if (readerRepository.getOne(id) == null) {
			return false;
		} else {
			readerRepository.delete(id);
			return true;
		}
	}

}
