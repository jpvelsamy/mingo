package com.mingo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Cookies;
import com.mingo.client.booking.BookingViewService;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Resrv implements EntryPoint {
    
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() 
    {

    	Cookies.setCookie("userId", "1");
    	
    	
    	
    	final TabSet topTabSet = new TabSet();  
        topTabSet.setTabBarPosition(Side.TOP);  
        topTabSet.setWidth("100%");
        topTabSet.setHeight("100%");
          
  
        Tab bookingPage = new Tab("Book tickets");          
        VStack bookingStack= new BookingViewService().getBookingPage();        
        bookingPage.setPane(bookingStack);  
  
        Tab myBookings = new Tab("My bookings");
        VStack mybookingsStack = createMyBookingPage();
        myBookings.setPane(mybookingsStack);
        
        Tab webCheckin = new Tab("Web Checkin");
        VStack webCheckingStack = createWebCheckinPage();        
        myBookings.setPane(webCheckingStack);
          
  
        topTabSet.addTab(bookingPage);  
        topTabSet.addTab(myBookings);  
        topTabSet.addTab(webCheckin);
        
        VLayout vLayout = new VLayout();  
        vLayout.setMembersMargin(15);  
        vLayout.addMember(topTabSet);
        vLayout.setWidth("100%");
        vLayout.setHeight("100%");
        vLayout.draw();  
        
    }

	

	private VStack createWebCheckinPage() {
		VStack vStack = new VStack();
        vStack.setLeft(175);
        vStack.setTop(50);
        vStack.setWidth("70%");
        vStack.setMembersMargin(10);
		return vStack;
	}



	

	protected void completePayment(String expiryDateValue,
			String cardHolderValue, String cardNumberValue, String cvvNum) {
		// TODO Auto-generated method stub
		
	}

	



	
	
	



	
	
	private VStack createMyBookingPage() {
		VStack vStack = new VStack();
        vStack.setLeft(175);
        vStack.setTop(50);
        vStack.setWidth("70%");
        vStack.setMembersMargin(10);
		return vStack;
	}
}
