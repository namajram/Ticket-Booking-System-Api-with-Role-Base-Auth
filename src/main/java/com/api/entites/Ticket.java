package com.api.entites;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "order" })
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ticketId;

	  @ElementCollection(targetClass = SeatNo.class, fetch = FetchType.EAGER)
	    @CollectionTable(name = "seat", joinColumns = @JoinColumn(name = "ticketId"))
	    @Column(name = "seat", nullable = false)
	    private List<SeatNo> seat= new ArrayList<>();
	  
	  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		@JoinColumn(name = "ticketOrderId", referencedColumnName = "orderId")
		private Order order;

	
	private String ticketStatus;

		

}
