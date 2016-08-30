package com.mingo.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mingo.server.service.MingoException;
import com.mingo.server.service.TicketResponse;
import com.mingo.server.service.ATReservationService;



public class LookupTicketController implements Controller
{

	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String flightPlanId=request.getParameter("flight_plan_id");
		int id = Integer.parseInt(flightPlanId);
		
		ATReservationService service = new ATReservationService();
		try {
			List<TicketResponse> tickets = service.lookupTicketPool(id);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tickets);
			response.getWriter().append(jsonInString);
		} catch (MingoException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
