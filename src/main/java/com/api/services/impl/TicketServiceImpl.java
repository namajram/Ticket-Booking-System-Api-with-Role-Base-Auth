package com.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entites.Ticket;
import com.api.repository.TicketRepository;
import com.api.services.TicketService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TicketServiceImpl implements TicketService {

	
	@Autowired
	private TicketRepository ticketRepository;
	

	
	
	@Override
	public Ticket updateTicket(long ticketId, Ticket ticket ) {
		Ticket existTicket =ticketRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Ticket Id not found"));
	
		existTicket.setOrder(ticket.getOrder());
		existTicket.setSeat(ticket.getSeat());
		existTicket.setTicketStatus(ticket.getTicketStatus());
		return ticketRepository.save(existTicket);
	}
	
	@Override
	  public boolean deleteTicket(long ticketId) {
  Optional<Ticket> optional = ticketRepository.findById(ticketId);
  if (optional.isPresent()) {
	  ticketRepository.delete(optional.get());
      return true;
  }
  return false;
}
	@Override
	 public Ticket getTicketById(long ticketId) {
	        Optional<Ticket> optional = ticketRepository.findById(ticketId);
	        return optional.orElseThrow(() -> new EntityNotFoundException("Ticket Id not found"));
	    }

	@Override
	public List<Ticket> getAllTicket() {
		
		return ticketRepository.findAll();
	}

}
