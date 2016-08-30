package com.mingo.server.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mingo.server.dao.FlightPlanDao;
import com.mingo.server.dao.ReservationDao;

public class ATReservationService 
{
	private FlightPlanDao flightDaas = new FlightPlanDao();
	private ReservationDao reserveDaas = new ReservationDao();
	
	public List<FlightPlan> lookupFlightPlan(SelectionCriteria criteria) throws MingoException
	{
		/*FlightPlan flightPlan  = new FlightPlan();
		flightPlan.setFlightPlanId(1);
		flightPlan.setTotalSeats(10);
		flightPlan.setAvailableSeats(10);
		flightPlan.setFirstOrigin("blah");
		flightPlan.setLastDestination("blahblah");
		flightPlan.setScheduleDeparture("somedate");
		List<FlightPlan> flightList= new ArrayList<FlightPlan>();
		flightList.add(flightPlan);*/
		
		List<FlightPlan> flightList=flightDaas.fetchFlightPlan(criteria.getTravelDate(), criteria.getOrigin(), criteria.getDestination());		
		return flightList;
	}
	
	
	public List<TicketResponse> lookupTicketPool(int flightPlanId)throws MingoException
	{
		/*TicketResponse ticket1= new TicketResponse(1,1,1,"business","left");
		TicketResponse ticket2= new TicketResponse(2,2,1,"business","right");
		TicketResponse ticket3= new TicketResponse(3,3,2,"business","left");
		TicketResponse ticket4= new TicketResponse(4,4,2,"business","right");
		List<TicketResponse> ticketList= new ArrayList<TicketResponse>();
		ticketList.add(ticket1);
		ticketList.add(ticket2);
		ticketList.add(ticket3);
		ticketList.add(ticket4);*/
		List<TicketResponse> ticketList=flightDaas.fetchTicketPool(flightPlanId);
		return ticketList;
	}
	
	public List<TicketResponse> blockTicket(int ticketId, int userId) throws MingoException
	{
		/*TicketResponse ticket1= new TicketResponse(ticketId,1,1,"business","left");
		TicketResponse ticket2= new TicketResponse(2,2,1,"business","right");
		List<TicketResponse> ticketList= new ArrayList<TicketResponse>();
		ticketList.add(ticket1);
		ticketList.add(ticket2);*/
		List<TicketResponse> ticketList = flightDaas.blockTickets(userId, ticketId);
		return ticketList;
	}
	
	/*public TicketResponse cancelTicket(TicketSelection choice)throws MingoException
	{
		return null;
	}
	
	public List<TicketResponse> findUserTicket(TicketSelection choice)throws MingoException
	{
		return null;
	}*/
	
	/*public FlightResponse cancelFlight(FlightInfo flightInfo)throws MingoException
	{
		return null;
	}*/
	
	
	public TicketResponse updatePassengerInfo(String fullName, String email, String primary, int user_id, int ticket_id) throws MingoException
	{
		TicketResponse response= reserveDaas.updatePassengerDetail(fullName, email, primary, user_id, ticket_id);
		return response;
		
	}
	
	
	public boolean completePayment(int user_id, String cardNum, String cardName, String cardDate, String cvvNum)throws MingoException
	{
		boolean status = Paymentgateway.completeTransaction(cardNum, cardName, cardDate, cvvNum);
		if(status)
			return reserveDaas.completeReservation(user_id, cardNum, cardName);
		else
		return status;
	}
	
	
	 
	public List<TicketResponse> cleanupTicketPool(SelectionCriteria criteria)throws MingoException
	{
		return null;
	}
	
	public List<TicketResponse> unblockTickets(SelectionCriteria criteria) throws MingoException
	{
		return null;
	}
	
	public static String convertDate(String inputDate) {
		String output;
		SimpleDateFormat format1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		String gmtString = "GMT+";
		SimpleDateFormat format2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");//2016-09-20
		Date incomingDate =null;
		try 
		{
			if(inputDate.contains(gmtString))
			{
				
				String firstPart=inputDate.substring(0, inputDate.indexOf(gmtString));
				String secondPart = inputDate.substring(inputDate.indexOf(gmtString), inputDate.length());
				String secondYearPart =secondPart.substring((secondPart.length()-5),secondPart.length());
				String finalString = firstPart+secondYearPart;
				System.out.println(finalString);
				incomingDate = format2.parse(finalString);
			}else
				incomingDate = format1.parse(inputDate);
			
		} catch (ParseException e) 
		{
			e.printStackTrace();
			incomingDate = new Date(System.currentTimeMillis());
		}
		output = outputFormat.format(incomingDate);
		return output;
	}
	
	public static String convertToCardDate(String inputDate)
	{
		String output;
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM");//2016-09-20
		Date incomingDate =null;
		try 
		{
			incomingDate = format.parse(inputDate);
			
		} catch (ParseException e) 
		{
			incomingDate = new Date(System.currentTimeMillis());
		}
		output = outputFormat.format(incomingDate);
		return output;
	}
}
