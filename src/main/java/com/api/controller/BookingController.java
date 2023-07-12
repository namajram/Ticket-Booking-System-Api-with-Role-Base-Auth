package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entites.Order;
import com.api.entites.SeatNoDto;
import com.api.entites.Ticket;
import com.api.services.impl.BookingServiceImpl;

@RestController
@RequestMapping("/api")
public class BookingController {
	
	@Autowired
	private BookingServiceImpl bookingServiceImpl;
	
	@PostMapping("/{userId}/{screenId}/booking")
	public ResponseEntity<?> bookingTicket(@PathVariable long userId,@PathVariable long screenId,@RequestBody SeatNoDto dto ){
		try {
			 Ticket bookingTicket = bookingServiceImpl.bookingTicket(userId, screenId, dto);
	        if (bookingTicket != null) {
	            return ResponseEntity.ok(bookingTicket);
	        }
	        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
				return ResponseEntity.internalServerError().body(e.getMessage());
			}
	}
	
	@PostMapping("/cancel/{ticketId}")
	public ResponseEntity<?> cancelBooking(@PathVariable long ticketId){
		try {
			  Order cancelBooking = bookingServiceImpl.cancelBooking(ticketId);
	        if (cancelBooking != null) {
	            return ResponseEntity.ok(cancelBooking);
	        }
	        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
				return ResponseEntity.internalServerError().body(e.getMessage());
			}
	}

}
