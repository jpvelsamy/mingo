package com.mingo.server.service;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mingo.server.dao.ReservationDao;
import com.mingo.test.TestUtil;

public class ATReservationServiceTest {

	private ApplicationContext applicationContext;
	private DataSource dataSource;
	private ATReservationService service;
	@Before
	public void setUp() throws Throwable 
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");				
		dataSource = (DataSource) applicationContext.getBean("txnDataSource");
		TestUtil.initialize(applicationContext, dataSource);
		service=new ATReservationService();
	}

	@After
	public void tearDown() throws Throwable 
	{
		TestUtil.cleanup(applicationContext, dataSource);
	}

	@Test
	public void testLookupFlightPlan() {
		try {
			SelectionCriteria criteria = new SelectionCriteria("2016-09-20", "New york", "Denver");
			service.lookupFlightPlan(criteria);
		} catch (MingoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testLookupTicketPool() {
		try {
			service.lookupTicketPool(20000);
		} catch (MingoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	

	@Test
	public void testBlockTicket() {
		try {
			service.blockTicket(20000, 1);
		} catch (MingoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testUpdatePassengerInfo() {
		//
		try {
			service.updatePassengerInfo("JP", "jpvel@jp.com", "yes", 1, 20000);
		} catch (MingoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testCompletePayment() {
		try {
			service.updatePassengerInfo("JP", "jpvel@jp.com", "yes", 1, 20000);
		} catch (MingoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testCleanupTicketPool() {
		//1, "1234", "hello"
		try {
			service.updatePassengerInfo("JP", "jpvel@jp.com", "yes", 1, 20000);
		} catch (MingoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	

	@Test
	public void testConvertToCardDate() {
		/*SelectionCriteria criteria1 = new SelectionCriteria("Tue Sep 20 12:00:00 GMT+530 2016", "New york", "San franciso");
		criteria1.getTravelDate();*/
		String date=ATReservationService.convertToCardDate("Tue Sep 20 12:00:00 IST 2016");
		assertEquals(date, "2016-09");
	}
	
	@Test
	public void testConvertDate() {
		/*SelectionCriteria criteria1 = new SelectionCriteria("Tue Sep 20 12:00:00 GMT+530 2016", "New york", "San franciso");
		criteria1.getTravelDate();*/
		String date=ATReservationService.convertDate("Tue Sep 20 12:00:00 GMT+530 2016");
		assertEquals(date, "2016-09-20");
	}

}
