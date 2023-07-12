package com.api.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
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
public class SeatNo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seatNoId;
	private String seatNo;
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = true)
	@JoinColumn(name = "screenSeatNoId", referencedColumnName = "screenId")
	@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "screen" })
	private Screen screen;
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = true)
	@JoinColumn(name = "userSeatNoId", referencedColumnName = "userId")
	@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "user" })
	private User user;
	private String seatStatus;
	
}
