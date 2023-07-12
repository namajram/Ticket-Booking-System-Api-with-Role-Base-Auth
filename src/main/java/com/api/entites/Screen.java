package com.api.entites;

import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long screenId;
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = true)
	@JoinColumn(name = "theatreScreenId", referencedColumnName = "theatreId")
	@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "theatre" })
	private Theatre theatre;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date date;

	private long screenNo;	

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Time showTime;

	private String movietitle;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private String moviePoster;
	
	

	
}
