package com.api.services;

import java.util.List;

import com.api.entites.Ticket;

public interface TicketService {

	List<Ticket> getAllTicket();

	Ticket updateTicket(long ticketId, Ticket ticket);

	boolean deleteTicket(long ticketId);

	Ticket getTicketById(long ticketId);

}
