package com.mingo.client.booking;

import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VStack;

public class SearchSection {
	private HStack firstStack;
	private BookingViewService bookingService;

	public SearchSection(BookingViewService viewService) {
		this.bookingService = viewService;
		this.addSearchSection(bookingService.getBookingPage());
	}

	private void addSearchSection(VStack vStack) {
		firstStack = new HStack();
		firstStack.setMembersMargin(5);
		firstStack.setHeight(30);

		DynamicForm searchform = new DynamicForm();
		searchform.setNumCols(6);

		final DateItem dateField = new DateItem("dateItem", "Date");
		dateField.setUseTextField(true);
		dateField.setUseMask(true);

		final TextItem origin = new TextItem("origin", "Origin");
		final TextItem destination = new TextItem("destination", "Destination");

		searchform.setFields(dateField, origin, destination);

		firstStack.addMember(searchform);

		IButton findBtn = new IButton("Find");
		findBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String dateValue = dateField.getValueAsDate().toString();
				String originValue = origin.getValueAsString();
				String destValue = destination.getValueAsString();
				bookingService.fetchFlightPlanData(dateValue, originValue,
						destValue);
			}

		});

		firstStack.addMember(findBtn);
		vStack.addMember(firstStack);

	}

	public void refresh() {

	}

}
