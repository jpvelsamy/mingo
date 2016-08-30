package com.mingo.server.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mingo.server.service.FlightPlan;
import com.mingo.server.service.MingoException;
import com.mingo.server.service.TicketResponse;
import com.mingo.test.TestUtil;

public class FlightPlanDaoTest  {

	private FlightPlanDao dao;
	private ApplicationContext applicationContext;
	private DataSource dataSource;
	
	@Before
	public void setUp() throws Throwable 
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");				
		dataSource = (DataSource) applicationContext.getBean("txnDataSource");
		TestUtil.initialize(applicationContext, dataSource);
		dao=new FlightPlanDao();
	}

	@After
	public void tearDown() throws Throwable 
	{
		TestUtil.cleanup(applicationContext, dataSource);
	}

	@Test
	public void testFetchFlightPlan() {
		try {
			List<FlightPlan> planList = dao.fetchFlightPlan("2016-09-20", "New york", "Denver");
			assertNotNull(planList);
			if(planList.isEmpty())
				fail("List cannot be empty if there is data in the store");
		} catch (MingoException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFetchTicketPool() {
		try {
			List<TicketResponse> ticketList = dao.fetchTicketPool(2);
			assertNotNull(ticketList);
			if(ticketList.isEmpty())
				fail("List cannot be empty if there is data in the store");
		} catch (MingoException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testBlockTickets() 
	{
		try {
			dao.blockTickets(1, 20000);
		} catch (MingoException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
