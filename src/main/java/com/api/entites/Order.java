package com.api.entites;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "UserOrderId", referencedColumnName = "UserId")
	@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "user" })
	private User user;
	
	private String paymentStatus;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "theatreTicketId", referencedColumnName = "theatreId")
	@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "theatre" })
	private Theatre theatre;


	@OneToMany(mappedBy = "order",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "ticket" })
	private List<Ticket> ticket  = new ArrayList<>();

	
	
}
