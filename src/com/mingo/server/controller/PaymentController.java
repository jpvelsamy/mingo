package com.mingo.server.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mingo.server.service.ATReservationService;
import com.mingo.server.service.MingoException;
import com.mingo.server.service.PaymentInfo;



public class PaymentController implements Controller
{

	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String cardNum = request.getParameter("cardNumber");
		String cardName = request.getParameter("cardHolder");
		String cardDate = request.getParameter("expiry");
		String outputDate =  ATReservationService.convertToCardDate(cardDate);
		String cvvNum = request.getParameter("cvv");
		
		String userId=null;
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			String name = cookie.getName();
			if(name.equals("userId")){
				userId=cookie.getValue();
			}
		}
		
		int uid = Integer.parseInt(userId);
		ATReservationService service = new ATReservationService();
		try 
		{
				ObjectMapper mapper = new ObjectMapper();
				boolean success = service.completePayment(uid, cardNum, cardName, outputDate, cvvNum);
				if(success)
				{
					PaymentInfo info = new PaymentInfo();
					info.setStatus("SUCCESS");
					info.setCardHolder(cardName);
					info.setCardNumber(cardNum);
					info.setExpiry(cardDate);
					info.setCvv(cvvNum);
					info.setReservation_id(System.currentTimeMillis());
					String output=mapper.writeValueAsString(info);
					response.getWriter().append(output);
				}
				else
				{
					PaymentInfo info = new PaymentInfo();
					info.setStatus("FAILURE");
					String output=mapper.writeValueAsString(info);
					response.getWriter().append(output);					
				}
		} catch (MingoException e) {
			e.printStackTrace();
		}
		return null;
	}

}
