package com.mingo.server.service;

import java.io.Serializable;

public class FlightPlan implements Serializable, MingoAuditable {

	private static final long serialVersionUID = 4091532213604913304L;
	private int flight_plan_id;
	private String scheduleDeparture;
	private int  totalSeats;
	private int availableSeats;
	private String firstOrigin;
	private String lastDestination;
	
	public FlightPlan()
	{
		
	}
	
	public FlightPlan(int flightPlanId, String scheduleDeparture,
			int totalSeats, int availableSeats, String firstOrigin,
			String lastDestination) {
		super();
		this.flight_plan_id = flightPlanId;
		this.scheduleDeparture = scheduleDeparture;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.firstOrigin = firstOrigin;
		this.lastDestination = lastDestination;
	}
	public int getFlight_plan_id() {
		return flight_plan_id;
	}
	public void setFlight_plan_id(int flightPlanId) {
		this.flight_plan_id = flightPlanId;
	}
	public String getScheduleDeparture() {
		return scheduleDeparture;
	}
	public void setScheduleDeparture(String scheduleDeparture) {
		this.scheduleDeparture = scheduleDeparture;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public String getFirstOrigin() {
		return firstOrigin;
	}
	public void setFirstOrigin(String firstOrigin) {
		this.firstOrigin = firstOrigin;
	}
	public String getLastDestination() {
		return lastDestination;
	}
	public void setLastDestination(String lastDestination) {
		this.lastDestination = lastDestination;
	}
	
	

}
