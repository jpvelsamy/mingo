package com.mingo.client.booking;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceSequenceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSProtocol;

public class BookingViewDataSource {

	private DataSource flightPlanDs;
	private DataSource ticketDs;
	private DataSource blockedDs;
	private DataSource paymentDs;

	public BookingViewDataSource() {
		initFlightPlanDs();
		initTicketDs();
		initBlockedDs();
		initPaymentDs();
	}

	private void initFlightPlanDs() {
		flightPlanDs = new DataSource();
		flightPlanDs.setDataFormat(DSDataFormat.JSON);
		flightPlanDs.setDataProtocol(DSProtocol.POSTPARAMS);
		flightPlanDs.setID("flightplan");
		String path = GWT.getModuleBaseURL() + "/lookupflight.do?";
		flightPlanDs.setDataURL(URL.encode(path));

		DataSourceSequenceField flightPlanID = new DataSourceSequenceField(
				BookingViewService.FLIGHT_PLAN_ID);
		flightPlanID.setPrimaryKey(true);
		flightPlanID.setHidden(true);

		DataSourceTextField departure = new DataSourceTextField("firstOrigin",
				"Departure", 128, true);
		DataSourceTextField arrival = new DataSourceTextField(
				"lastDestination", "Arrival", 128);
		DataSourceTextField totalSeats = new DataSourceTextField("totalSeats",
				"Total seats", 30);
		DataSourceTextField freeSeats = new DataSourceTextField(
				"availableSeats", "Free seats", 30);

		flightPlanDs.setFields(flightPlanID, departure, arrival, totalSeats,
				freeSeats);
		
	}

	private void initTicketDs() {
		ticketDs = new DataSource();
		ticketDs.setDataFormat(DSDataFormat.JSON);
		ticketDs.setDataProtocol(DSProtocol.POSTPARAMS);
		ticketDs.setID("ticketpool");
		String path = GWT.getModuleBaseURL() + "/lookuptickets.do?";
		ticketDs.setDataURL(URL.encode(path));

		DataSourceSequenceField ticketId = new DataSourceSequenceField(
				BookingViewService.TICKET_ID);
		ticketId.setPrimaryKey(true);
		ticketId.setHidden(true);

		DataSourceTextField seatNumber = new DataSourceTextField("seatNumber",
				"Number", 128, true);
		DataSourceTextField rowNumber = new DataSourceTextField("rowNumber",
				"Row", 128);
		DataSourceTextField seatclass = new DataSourceTextField("seatClass",
				"Seat Type", 30);
		DataSourceTextField side = new DataSourceTextField("seatSide",
				"Seat side", 30);

		ticketDs.setFields(ticketId, seatNumber, rowNumber, seatclass, side);

		
	}

	private void initBlockedDs() {

		blockedDs = new DataSource();
		blockedDs.setDataFormat(DSDataFormat.JSON);
		blockedDs.setDataProtocol(DSProtocol.POSTPARAMS);
		blockedDs.setID("blockticket");
		String path = GWT.getModuleBaseURL() + "/blockticket.do?";
		blockedDs.setDataURL(URL.encode(path));

		DataSourceSequenceField ticketIdDs = new DataSourceSequenceField(
				BookingViewService.TICKET_ID);
		ticketIdDs.setPrimaryKey(true);
		ticketIdDs.setHidden(true);

		DataSourceTextField seatNumber = new DataSourceTextField("seatNumber",
				"Number", 10, true);
		DataSourceTextField name = new DataSourceTextField("fullName",
				"Full Name", 60, true);
		DataSourceTextField emailId = new DataSourceTextField("email",
				"Email Id", 40, true);
		DataSourceTextField primaryHolder = new DataSourceTextField("numUno",
				"Primary", 15, true);
		blockedDs.setFields(ticketIdDs, seatNumber, name, emailId,
				primaryHolder);

		
	}

	private void initPaymentDs(){
		this.paymentDs = new DataSource();
		this.paymentDs.setDataFormat(DSDataFormat.JSON);
		this.paymentDs.setDataProtocol(DSProtocol.POSTPARAMS);
		this.paymentDs.setID("confirmreservation");
		String path = GWT.getModuleBaseURL() + "/completepayment.do?";
		this.paymentDs.setDataURL(URL.encode(path));
		
		DataSourceSequenceField reserverId = new DataSourceSequenceField(
				"reservation_id");
		reserverId.setPrimaryKey(true);
		reserverId.setHidden(true);
		
		DataSourceTextField cardHolder = new DataSourceTextField("cardHolder",
				"Card holder name", 10, true);
		
		DataSourceTextField cardNumber = new DataSourceTextField("cardNumber",
				"Card number", 10, true);
		
		DataSourceTextField expiryDate = new DataSourceTextField("expiry",
				"Expiry date", 10, true);
		
		DataSourceTextField cvvNumber = new DataSourceTextField("cvv",
				"CVV number", 10, true);
		
		DataSourceTextField message = new DataSourceTextField("status",
				"Status", 30, true);
		
		paymentDs.setFields(reserverId, cardHolder, cardNumber, expiryDate, cvvNumber,message	);
		
	}
	public DataSource getPaymentDs() {
		return paymentDs;
	}

	public DataSource getFlightPlanDs() {
		return flightPlanDs;
	}

	public void setFlightPlanDs(DataSource flightPlanDs) {
		this.flightPlanDs = flightPlanDs;
	}

	public DataSource getTicketDs() {
		return ticketDs;
	}

	public void setTicketDs(DataSource ticketDs) {
		this.ticketDs = ticketDs;
	}

	public DataSource getBlockDs() {
		return blockedDs;
	}

}
