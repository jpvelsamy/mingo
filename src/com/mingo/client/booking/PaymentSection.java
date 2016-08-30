package com.mingo.client.booking;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VStack;

public class PaymentSection {

	private DynamicForm paymentForm;
	private BookingViewService bookingService;

	public PaymentSection(BookingViewService bookingService) {
		this.bookingService = bookingService;
		this.addPaymentSection(this.bookingService.getBookingPage());
	}

	private void addPaymentSection(VStack vStack) {
		// HStack paymentStack = new HStack();
		paymentForm = new DynamicForm();
		paymentForm.setNumCols(8);
		paymentForm.setHeight(40);
		paymentForm.setDataSource(this.bookingService.getDataSourceFacade().getPaymentDs());
		final TextItem cardHolder = new TextItem("cardHolder",
				"Card holder name");
		cardHolder.setAlign(Alignment.LEFT);
		cardHolder.setTitleAlign(Alignment.LEFT);

		final TextItem cardNumber = new TextItem("cardNumber", "Card number");
		cardNumber.setAlign(Alignment.LEFT);
		cardNumber.setTitleAlign(Alignment.LEFT);
		final DateItem expiryDate = new DateItem("expiry", "Expiry date");
		expiryDate.setUseTextField(true);
		expiryDate.setUseMask(true);
		expiryDate.setAlign(Alignment.LEFT);
		expiryDate.setTitleAlign(Alignment.LEFT);

		final PasswordItem cvvNumber = new PasswordItem("cvv", "CVV number");
		// cvvNumber.setHint("3 digit number @ the card backside");
		cvvNumber.setAlign(Alignment.LEFT);
		cvvNumber.setTitleAlign(Alignment.LEFT);
		
		final TextItem status = new TextItem("status", "Status");
		// cvvNumber.setHint("3 digit number @ the card backside");
		status.setAlign(Alignment.LEFT);
		status.setTitleAlign(Alignment.LEFT);
		status.setValue("In progress");

		paymentForm.setFields(cardHolder, cardNumber, expiryDate, cvvNumber, status);
		// paymentStack.addMember(paymentForm);

		IButton payBtn = new IButton("Pay");

		payBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				/*String expiryDateValue = expiryDate.getValueAsDate().toString();
				String cardHolderValue = cardHolder.getValueAsString();
				String cardNumberValue = cardNumber.getValueAsString();
				String cvvNum = cvvNumber.getValueAsString();*/
				/*completePayment(expiryDateValue, cardHolderValue,
						cardNumberValue, cvvNum);*/
				// send the information for storage
				paymentForm.saveData();
			}

		});
		// paymentStack.addMember(payBtn);
		vStack.addMember(paymentForm);
		vStack.addMember(payBtn);
	}

	protected void completePayment(String expiryDateValue,
			String cardHolderValue, String cardNumberValue, String cvvNum) {
		// TODO Auto-generated method stub

	}

	public void refresh() {

	}

	public void reset() {

	}
}
