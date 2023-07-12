package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entites.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
