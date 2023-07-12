package com.api.services;

import java.util.List;

import com.api.entites.SeatNo;

public interface SeatNoService {

	SeatNo createSeatNo(SeatNo seatNo, long screenId);

	SeatNo updateSeatNo(SeatNo seatNo, long screenId, long seatNoId);

	boolean deleteSeatNo(long screenId);

	SeatNo getSeatNoById(long screenId);

	List<SeatNo> getAllSeatNo();

	List<SeatNo> getScreenSeatById(long screenId);

}
