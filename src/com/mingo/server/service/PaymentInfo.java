package com.mingo.server.service;

import java.io.Serializable;

public class PaymentInfo implements Serializable {

	private static final long serialVersionUID = -3214278559949299649L;
	private String status;
	private long reservation_id;
	private String cardHolder;
	private String cardNumber;
	private String expiry;
	private String cvv;
	
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(long reservation_id) {
		this.reservation_id = reservation_id;
	}
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardName) {
		this.cardHolder = cardName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNum) {
		this.cardNumber = cardNum;
	}
	
	

}
