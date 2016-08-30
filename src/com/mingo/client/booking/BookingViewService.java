package com.mingo.client.booking;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.layout.VStack;

public class BookingViewService {
	public static final String FLIGHT_PLAN_ID = "flight_plan_id";
	public static final String DESTINATION = "destination";
	public static final String ORIGIN = "origin";
	public static final String TRAVEL_DATE = "travelDate";
	public static final String TICKET_ID = "ticket_id";
	
	private SearchSection searchSection;
	private FlightPlanSection flightPlanSection;
	private BlockedTicketDetailSection blkTicketDetailSection;
	private PaymentSection paymentSection;
	private VStack bookingViewStack;

	private BookingViewDataSource dataSourceFacade;

	public BookingViewService() {
		dataSourceFacade = new BookingViewDataSource();
		this.createBookingPage();
	}

	private VStack createBookingPage() {
		bookingViewStack = new VStack();
		bookingViewStack.setLeft(175);
		bookingViewStack.setTop(50);
		bookingViewStack.setWidth("70%");
		bookingViewStack.setMembersMargin(10);

		/*
		 * Label label = new Label(); label.setContents(
		 * "<h3>Find and book your Airline ticket</h3>");
		 * vStack.addMember(label);
		 */

		this.searchSection = new SearchSection(this);
		this.flightPlanSection = new FlightPlanSection(this);
		this.blkTicketDetailSection = new BlockedTicketDetailSection(this);
		this.paymentSection = new PaymentSection(this);

		return bookingViewStack;
	}

	public void fetchBlockListData(String ticketId) {

		Criteria selxnCriteria = new Criteria();
		selxnCriteria.addCriteria(TICKET_ID, ticketId);
		blkTicketDetailSection.refresh(selxnCriteria);
	}

	public void fetchFlightPlanData(String dateValue, String originValue,
			String destValue) {

		Criteria selectionCriteria = new Criteria();
		selectionCriteria.addCriteria(TRAVEL_DATE, dateValue);
		selectionCriteria.addCriteria(ORIGIN, originValue);
		selectionCriteria.addCriteria(DESTINATION, destValue);
		flightPlanSection.refreshFlightPlan(selectionCriteria);
	}

	public void fetchTicketPoolData(String filghtPlanId) {

		Criteria selxnCriteria = new Criteria(FLIGHT_PLAN_ID, filghtPlanId);
		flightPlanSection.refreshTicketPool(selxnCriteria);

	}

	public void fetchSearchMaster() {
		this.searchSection.refresh();
	}

	public void resetPaymentSection() {
		this.paymentSection.reset();
	}

	public FlightPlanSection getFlightPlanSection() {
		return flightPlanSection;
	}

	public BookingViewDataSource getDataSourceFacade() {
		return dataSourceFacade;
	}

	public VStack getBookingPage() {
		return this.bookingViewStack;
	}

}
