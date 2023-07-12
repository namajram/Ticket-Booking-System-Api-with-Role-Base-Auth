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
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value= {"handler","hibernateLazyInitializer","screen"})
public class Theatre {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	    private long theatreId;
	    
	    private String theatreName;
	   
	    private String theatreCity;
	    
	    private Long pincode;

	   	@OneToMany(mappedBy = "theatre",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
	    private List<Screen> screen  = new ArrayList<>();
}
