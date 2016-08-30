package com.mingo.server.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class SelectionCriteriaTest {

	@Test
	public void test() {
		/*SelectionCriteria criteria = new SelectionCriteria("Tue Sep 20 12:00:00 IST 2016", "New york", "San franciso");
		System.out.println(criteria.getTravelDate());*/
		
		SelectionCriteria criteria1 = new SelectionCriteria("Tue Sep 20 12:00:00 GMT+530 2016", "New york", "San franciso");
		System.out.println(criteria1.getTravelDate());
		
		/*SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
		String output = format.format(new Date(System.currentTimeMillis()));
		System.out.println(output);*/
	}

}
