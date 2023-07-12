package com.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entites.Theatre;
import com.api.repository.TheatreRepository;
import com.api.services.TheatreService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TheatreServiceImpl implements TheatreService {
	@Autowired
	private TheatreRepository theatreRepository;
	
	@Override
	public Theatre createTheatre(Theatre theatre) {
		
		 return theatreRepository.save(theatre);
	}
	
	@Override
	public Theatre updateTheatre(long theatreId, Theatre theatre ) {
		Theatre existTheatre =theatreRepository.findById(theatreId).orElseThrow(() -> new EntityNotFoundException("Theatre Id not found"));
	
	
		existTheatre.setTheatreCity(theatre.getTheatreCity());
		existTheatre.setTheatreName(theatre.getTheatreName());
		return theatreRepository.save(existTheatre);
	}
	
	@Override
	  public boolean deleteTheatre(long theatreId) {
  Optional<Theatre> optional = theatreRepository.findById(theatreId);
  if (optional.isPresent()) {
	  theatreRepository.delete(optional.get());
      return true;
  }
  return false;
}
	@Override
	 public Theatre getTheatreById(long theatreId) {
	        Optional<Theatre> optional = theatreRepository.findById(theatreId);
	        return optional.orElseThrow(() -> new EntityNotFoundException("Theatre Id not found"));
	    }

	@Override
	public List<Theatre> getAllTheatre() {
		
		return theatreRepository.findAll();
	}

}
