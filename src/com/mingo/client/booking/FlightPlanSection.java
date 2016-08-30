package com.mingo.client.booking;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VStack;

public class FlightPlanSection {

	private ListGrid flightPlanList;
	private ListGrid ticketList;
	private BookingViewService bookingService;
	private BookingViewDataSource dsFacade;

	public FlightPlanSection(BookingViewService bookingService) {
		this.bookingService = bookingService;
		this.dsFacade = this.bookingService.getDataSourceFacade();
		VStack bookingViewStack = this.bookingService.getBookingPage();
		this.addListingSection(bookingViewStack);
	}

	public ListGrid getFlightPlanList() {
		return flightPlanList;
	}

	private void addListingSection(VStack vStack) {
		HStack hStack = new HStack();
		hStack.setWidth100();

		flightPlanList = new ListGrid();
		flightPlanList.setHeight(150);
		flightPlanList.setCanEdit(false);
		flightPlanList.setWidth("50%");
		flightPlanList.setDataSource(dsFacade.getFlightPlanDs());

		flightPlanList.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				Record record = event.getRecord();
				final String flightPlanId = record.getAttribute(BookingViewService.FLIGHT_PLAN_ID);
				bookingService.fetchTicketPoolData(flightPlanId);

			}
		});
		hStack.addMember(flightPlanList);

		ticketList = new ListGrid();
		ticketList.setHeight(150);
		ticketList.setCanEdit(true);
		ticketList.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		ticketList.setWidth("50%");
		ticketList.setDataSource(dsFacade.getTicketDs());

		/*
		 * ticketList.addSelectionChangedHandler(new SelectionChangedHandler() {
		 * 
		 * @Override public void onSelectionChanged(SelectionEvent event) {
		 * ticketList.getSelectedRecord() } });
		 */

		ticketList.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				Record record = event.getRecord();

				String ticketId = record.getAttribute(BookingViewService.TICKET_ID);
				bookingService.fetchBlockListData(ticketId);

			}
		});

		hStack.addMember(ticketList);
		vStack.addMember(hStack);
	}

	public void refreshFlightPlan(Criteria selectionCriteria) {

		flightPlanList.fetchData(selectionCriteria);
	}

	public void refreshTicketPool(Criteria selectionCriteria) {
		ticketList.fetchData(selectionCriteria);
	}
}
