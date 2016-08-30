package com.mingo.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mingo.server.service.FlightPlan;
import com.mingo.server.service.MingoException;
import com.mingo.server.service.SelectionCriteria;
import com.mingo.server.service.ATReservationService;



public class LookupFlightController implements Controller
{

	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String date=request.getParameter("travelDate");
		String origin = request.getParameter("origin");
		String destination = request.getParameter("destination");
		SelectionCriteria criteria = new SelectionCriteria(date, origin, destination);
		ATReservationService service = new ATReservationService();
		try {
			List<FlightPlan> flightList = service.lookupFlightPlan(criteria);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(flightList);
			System.out.println(jsonInString);
			response.getWriter().append(jsonInString);
		} catch (MingoException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
