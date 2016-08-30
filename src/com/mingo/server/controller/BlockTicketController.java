package com.mingo.server.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mingo.server.service.MingoException;
import com.mingo.server.service.TicketResponse;
import com.mingo.server.service.ATReservationService;



public class BlockTicketController implements Controller
{

	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String ticketId=request.getParameter("ticket_id");
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String primaryPassenger = request.getParameter("numUno");
		
		String userId=null;
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			String name = cookie.getName();
			if(name.equals("userId")){
				userId=cookie.getValue();
			}
		}
		int tid =0;
		if(ticketId!=null && ticketId.trim().length()>0)
			tid= Integer.parseInt(ticketId);
		
		int uid = Integer.parseInt(userId);
		ATReservationService service = new ATReservationService();
		if(fullName!=null && fullName.trim().length()>0)	{
			try {
				TicketResponse tresponse =service.updatePassengerInfo(fullName, email, primaryPassenger, uid, tid);
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(tresponse);
				response.getWriter().append(jsonInString);
			} catch (MingoException e) {
				e.printStackTrace();
			}
		}else{
			
			try {
				List<TicketResponse> tickets = service.blockTicket(tid, uid);			
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(tickets);
				response.getWriter().append(jsonInString);
			} catch (MingoException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
