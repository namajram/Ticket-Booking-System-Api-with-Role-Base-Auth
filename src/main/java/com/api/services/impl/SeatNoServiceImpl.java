package com.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entites.Screen;
import com.api.entites.SeatNo;
import com.api.repository.ScreenRepository;
import com.api.repository.SeatNoRepository;
import com.api.services.SeatNoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SeatNoServiceImpl implements SeatNoService {
	@Autowired
	private SeatNoRepository seatNoRepository;

	@Autowired
	private ScreenRepository screenRepository;
	
	@Override
	public SeatNo createSeatNo(SeatNo seatNo,long screenId) {
		Screen screen = screenRepository.findById(screenId).orElseThrow(() -> new EntityNotFoundException("Screen Id not found"));
		seatNo.setScreen(screen);
		 return seatNoRepository.save(seatNo);
	}
	
	@Override
	public SeatNo updateSeatNo(SeatNo seatNo,long screenId,long seatNoId ) {
		SeatNo existSeatNo =seatNoRepository.findById(seatNoId).orElseThrow(() -> new EntityNotFoundException("SeatNo Id not found"));
		Screen screen = screenRepository.findById(screenId).orElseThrow(() -> new EntityNotFoundException("Screen Id not found"));	
		existSeatNo.setScreen(screen);
		existSeatNo.setSeatNo(seatNo.getSeatNo());
		existSeatNo.setSeatStatus(seatNo.getSeatStatus());
		existSeatNo.setUser(seatNo.getUser());
		return seatNoRepository.save(existSeatNo);
	}
	
	@Override
	  public boolean deleteSeatNo(long seatNoId) {
  Optional<SeatNo> optional = seatNoRepository.findById(seatNoId);
  if (optional.isPresent()) {
	  seatNoRepository.delete(optional.get());
      return true;
  }
  return false;
}
	@Override
	 public SeatNo getSeatNoById(long seatNoId) {
	        Optional<SeatNo> optional = seatNoRepository.findById(seatNoId);
	        return optional.orElseThrow(() -> new EntityNotFoundException("SeatNo Id not found"));
	    }
	@Override
	 public List<SeatNo> getScreenSeatById(long screenId) {
	        List<SeatNo> list = seatNoRepository.findByScreen_ScreenId(screenId);
	        return list;
	    }

	@Override
	public List<SeatNo> getAllSeatNo() {
		
		return seatNoRepository.findAll();
	}


}
