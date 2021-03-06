package com.hamsuhi.entity;
// Generated Mar 7, 2018 1:58:59 PM by Hibernate Tools 5.2.8.Final

import java.util.Date;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

/**
 * BookBorrowing generated by hbm2java
 */
@Entity
@Table(name = "BookBorrowing", catalog = "booking_manager")
public class BookBorrowing implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private BookBorrowingId id;
	private Booking booking;
	private Reader reader;
	private Date dateBorrowing;
	private Date datePay;

	public BookBorrowing() {
	}

	public BookBorrowing(BookBorrowingId id, Booking booking, Reader reader) {
		this.id = new BookBorrowingId(booking.getBookingId(), reader.getNumberCard());
		this.booking = booking;
		this.reader = reader;
	}

	public BookBorrowing(BookBorrowingId id,Booking booking, Reader reader, Date dateBorrowing, Date datePay) {
		this.id = new BookBorrowingId(booking.getBookingId(), reader.getNumberCard());
		this.booking = booking;
		this.reader = reader;
		this.dateBorrowing = dateBorrowing;
		this.datePay = datePay;
	}

	public BookBorrowing(BookBorrowingId id,Date dateBorrowing, Date datePay) {
		//this.id = new BookBorrowingId(booking.getBookingId(), reader.getNumberCard());
		this.id =id;
		this.dateBorrowing = dateBorrowing;
		this.datePay = datePay;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "numberCard", column = @Column(name = "number_card", nullable = false)),
			@AttributeOverride(name = "bookingId", column = @Column(name = "booking_id", nullable = false)) })
	public BookBorrowingId getId() {
		return this.id;
	}

	public void setId(BookBorrowingId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "number_card", nullable = false, insertable = false, updatable = false)
	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "booking_id", nullable = false, insertable = false, updatable = false)
	public Reader getReader() {
		return this.reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_borrowing", length = 10)
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	public Date getDateBorrowing() {
		return this.dateBorrowing;
	}

	public void setDateBorrowing(Date dateBorrowing) {
		this.dateBorrowing = dateBorrowing;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_pay", length = 10)
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
	public Date getDatePay() {
		return this.datePay;
	}

	public void setDatePay(Date datePay) {
		this.datePay = datePay;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		BookBorrowing bookReader = (BookBorrowing) obj;
		return Objects.equals(booking, bookReader.booking) && Objects.equals(reader, bookReader.reader);
	}

	@Override
	public int hashCode() {
		return Objects.hash(booking, reader);
	}

}
