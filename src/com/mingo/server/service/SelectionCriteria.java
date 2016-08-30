package com.mingo.server.service;

import java.io.Serializable;

public class SelectionCriteria implements Serializable {

	private static final long serialVersionUID = -7359430016813563589L;
	private String outputDate;
	private String travelDate;
	private String origin;
	private String destination;
	public String getTravelDate() {
		if(outputDate==null)
		{
			//Tue Sep 20 12:00:00 IST 2016
			String output=null;
			output = ATReservationService.convertDate(travelDate);
			this.outputDate=output;
		}
		return outputDate;
	}
	
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
		
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public SelectionCriteria(String travelDate, String origin,
			String destination) {
		super();
		this.travelDate = travelDate;
		this.origin = origin;
		this.destination = destination;
	}
	
	

}
