package com.api.services;

import java.util.List;

import com.api.entites.Theatre;

public interface TheatreService {

	boolean deleteTheatre(long theatreId);

	Theatre getTheatreById(long theatreId);

	List<Theatre> getAllTheatre();

	Theatre updateTheatre(long theatreId, Theatre theatre);

	Theatre createTheatre(Theatre theatre);

}
