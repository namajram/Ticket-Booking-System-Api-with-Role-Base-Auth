package com.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entites.Order;
import com.api.entites.Screen;
import com.api.entites.SeatNo;
import com.api.entites.SeatNoDto;
import com.api.entites.Ticket;
import com.api.entites.User;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.OrderRepository;
import com.api.repository.ScreenRepository;
import com.api.repository.SeatNoRepository;
import com.api.repository.TicketRepository;
import com.api.repository.UserRepository;
import com.api.services.BookingService;

import jakarta.persistence.EntityNotFoundException;
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private ScreenRepository screenRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private SeatNoRepository seatNoRepository;
	@Autowired
	private UserRepository userRepository;
	
	

	@Override
	public Ticket bookingTicket(long userId,long screenId,SeatNoDto dto ) {
		Order order=new Order();
		List<SeatNo> No = new ArrayList<>();
		Ticket ticket =new Ticket();
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Id not found"));
		Screen screen = screenRepository.findById(screenId).orElseThrow(() -> new ResourceNotFoundException("Screen Id not found"));;
		;
		for (long seat : (dto.getSeatNo())) {
			SeatNo seatNo = seatNoRepository.findById(seat).orElseThrow(() -> new ResourceNotFoundException("SeatNo Id not found"));
		if ((seatNo.getSeatStatus()).contentEquals("Available")) {
			seatNo.setSeatStatus("Unavailable");
			seatNo.setUser(user);
			SeatNo save = seatNoRepository.save(seatNo);
			No.add(save);
		} else {throw new ResourceNotFoundException("seat is already booked");

		}
			
		}
		order.setPaymentStatus("completed");
		order.setTheatre(screen.getTheatre());
	
		order.setUser(user);
		Order order2 = orderRepository.save(order);
		ticket.setSeat(No);
		ticket.setTicketStatus("Booked");
		ticket.setOrder(order2);
		Ticket ticket2 = ticketRepository.save(ticket);
		
		return ticket2;
	
	}
	@Override
	public Order cancelBooking(long ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Ticket Id not found"));
		ticket.setTicketStatus("Cancel");
		
		for (SeatNo seat : ticket.getSeat()) {
			SeatNo seatNo = seatNoRepository.findById(seat.getSeatNoId()).get();
			seatNo.setSeatStatus("Available");
			seatNo.setUser(null);
			seatNoRepository.save(seatNo);
			
		}
		ticket.setSeat(null);
		Order order = orderRepository.findById(ticket.getOrder().getOrderId()).orElseThrow(() -> new EntityNotFoundException("Ticket Id not found"));
		order.setPaymentStatus("Refund Pending");
		
		ticketRepository.save(ticket);
		return orderRepository.save(order);
		
	}
	
	
}