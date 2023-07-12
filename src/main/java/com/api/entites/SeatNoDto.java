package com.api.entites;

import java.util.List;


public class SeatNoDto {

	
	private List<Long> seatNo ;

	public List<Long> getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(List<Long> seatNo) {
		this.seatNo = seatNo;
	}

	public SeatNoDto(List<Long> seatNo) {
		super();
		this.seatNo = seatNo;
	}

	public SeatNoDto() {
		super();
	}
	
}
