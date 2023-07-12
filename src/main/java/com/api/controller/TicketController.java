package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entites.Ticket;
import com.api.services.impl.TicketServiceImpl;
@RestController
@RequestMapping("/api")
public class TicketController {

	@Autowired
	private TicketServiceImpl ticketServiceImpl;
	
	@PutMapping("/Ticket/update/{ticketId}")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> updateTicket(@PathVariable long ticketId,@RequestBody Ticket ticket ) {
		try {
			Ticket updatedUser = ticketServiceImpl.updateTicket(ticketId, ticket);
	        if (updatedUser != null) {
	            return ResponseEntity.ok(updatedUser);
	        }
	        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
				return ResponseEntity.internalServerError().body(e.getMessage());
			}
	}
	
	@DeleteMapping("/Ticket/delete/{ticketId}")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")	  
    public ResponseEntity<?> deleteTicket(@PathVariable Long ticketId) {
	  try {
        boolean deleted = ticketServiceImpl.deleteTicket(ticketId);
        if (deleted) {
        	 return ResponseEntity.ok("successfully deleted");
        }
        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	    }
  
  @GetMapping("/Ticket/get/{ticketId}")
 
    public ResponseEntity<?> getTicketById(@PathVariable Long ticketId) {
	  try {
		  Ticket user = ticketServiceImpl.getTicketById(ticketId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	    }
  
  @GetMapping("/Ticket/get/all")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAllTicket() {
	  try {
		  List<Ticket> users = ticketServiceImpl.getAllTicket();
	  
        return ResponseEntity.ok(users);
  } catch (Exception e) {
		return ResponseEntity.internalServerError().body(e.getMessage());
	}

}
}
