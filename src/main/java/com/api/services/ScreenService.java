package com.api.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.api.entites.Screen;

public interface ScreenService {

	boolean deleteScreen(long screenId);

	Screen getScreenById(long screenId);

	List<Screen> getAllScreen();

	Screen createScreen(Screen screen, long theatreId, MultipartFile file) throws IOException;

	Screen updateScreen(long screenId, Screen screen, long theatreId, MultipartFile file) throws IOException;

}
