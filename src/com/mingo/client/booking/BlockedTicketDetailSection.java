package com.mingo.client.booking;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VStack;

public class BlockedTicketDetailSection {

	private ListGrid blockedList;
	private BookingViewService bookingService;

	public BlockedTicketDetailSection(BookingViewService bookingService) {
		this.bookingService = bookingService;
		this.addConfirmSection(this.bookingService.getBookingPage());
	}

	private void addConfirmSection(VStack vStack) {
		blockedList = new ListGrid();
		blockedList.setHeight(150);
		blockedList.setCanEdit(true);
		blockedList.setDataSource(bookingService.getDataSourceFacade()
				.getBlockDs());

		vStack.addMember(blockedList);
		
		/*IButton confirm = new IButton("Confirm");
		confirm.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RecordList data = new RecordList();
				int[] edited = blockedList.getAllEditRows();
				for (int i = 0; i < edited.length; i++)
					data.add(blockedList.getEditedRecord(edited[i]));
			}
			// send data for storage
		});
		vStack.addMember(confirm);*/
	}

	public void refresh(Criteria selectionCriteria) 
	{
		blockedList.fetchData(selectionCriteria);
	}
}
