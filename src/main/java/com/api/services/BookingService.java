package com.api.services;

import java.util.List;

import com.api.entites.Order;
import com.api.entites.SeatNo;
import com.api.entites.SeatNoDto;
import com.api.entites.Ticket;

public interface BookingService {


	Order cancelBooking(long ticketId);

	
	Ticket bookingTicket(long userId, long screenId, SeatNoDto dto);


	


}
