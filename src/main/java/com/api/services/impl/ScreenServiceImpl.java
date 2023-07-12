package com.api.services.impl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.entites.Screen;
import com.api.entites.Theatre;
import com.api.repository.ScreenRepository;
import com.api.repository.TheatreRepository;
import com.api.services.ScreenService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ScreenServiceImpl implements ScreenService {
	@Autowired
	private ScreenRepository screenRepository;
	
	@Autowired
	private TheatreRepository theatreRepository;
	
	@Override
	public Screen createScreen(Screen screen,long theatreId,MultipartFile file) throws IOException  {
		Theatre findById = theatreRepository.findById(theatreId).orElseThrow(() -> new EntityNotFoundException("Theatre Id not found"));
		screen.setTheatre(findById);
		byte[] bytes = file.getBytes();
		String encodeToString = Base64.getEncoder().encodeToString(bytes);
		screen.setMoviePoster(encodeToString);
		 return screenRepository.save(screen);
	}
	
	@Override
	public Screen updateScreen(long screenId, Screen screen,long theatreId,MultipartFile file) throws IOException {
		Screen existScreen =screenRepository.findById(screenId).orElseThrow(() -> new EntityNotFoundException("Screen Id not found"));
		Theatre findById = theatreRepository.findById(theatreId).orElseThrow(() -> new EntityNotFoundException("Theatre Id not found"));
		existScreen.setDate(screen.getDate());
		existScreen.setTheatre(findById);
		byte[] bytes = file.getBytes();
		String encodeToString = Base64.getEncoder().encodeToString(bytes);
		existScreen.setMoviePoster(encodeToString);
		existScreen.setMovietitle(screen.getMovietitle());
		
		return screenRepository.save(existScreen);
	}
	@Override
	  public boolean deleteScreen(long screenId) {
Optional<Screen> optional = screenRepository.findById(screenId);
if (optional.isPresent()) {
	screenRepository.delete(optional.get());
    return true;
}
return false;
}
	@Override
	 public Screen getScreenById(long screenId) {
	        Optional<Screen> optional = screenRepository.findById(screenId);
	        return optional.orElseThrow(() -> new EntityNotFoundException("Theatre Id not found"));
	    }

	@Override
	public List<Screen> getAllScreen() {
		
		return screenRepository.findAll();
	}
}
