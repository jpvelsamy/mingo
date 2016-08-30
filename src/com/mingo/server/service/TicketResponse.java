package com.mingo.server.service;

import java.io.Serializable;

public class TicketResponse implements Serializable {

	private static final long serialVersionUID = -7235443501944934265L;
	private int ticket_id;
	private int seatNumber;
	private int rowNumber;
	private String seatClass;
	private String seatSide;
	private String fullName;
	private String email;
	private String numUno;
	
	public TicketResponse(int ticketId, String fullName, String email,
			String numUno) {
		super();
		this.ticket_id = ticketId;
		this.fullName = fullName;
		this.email = email;
		this.numUno = numUno;
	}
	
	public TicketResponse(int ticketId, int seatNumber, String fullName, String email,
			String numUno) {
		super();
		this.ticket_id = ticketId;
		this.fullName = fullName;
		this.email = email;
		this.numUno = numUno;
		this.seatNumber=seatNumber;
	}
	
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticketId) {
		this.ticket_id = ticketId;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
	public String getSeatSide() {
		return seatSide;
	}
	public void setSeatSide(String seatSide) {
		this.seatSide = seatSide;
	}
	public TicketResponse(int ticketId, int seatNumber, int rowNumber,
			String seatClass, String seatSide) {
		super();
		this.ticket_id = ticketId;
		this.seatNumber = seatNumber;
		this.rowNumber = rowNumber;
		this.seatClass = seatClass;
		this.seatSide = seatSide;
	}
	public TicketResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getFullName() {
		return fullName;
	}
	public String getEmail() {
		return email;
	}
	public String getNumUno() {
		return numUno;
	}
	
	
	

}
