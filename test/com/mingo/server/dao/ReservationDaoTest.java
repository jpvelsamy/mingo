package com.mingo.server.dao;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mingo.server.service.MingoException;
import com.mingo.test.TestUtil;

public class ReservationDaoTest {

	private ReservationDao dao;
	private ApplicationContext applicationContext;
	private DataSource dataSource;
	
	
	@Before
	public void setUp() throws Throwable 
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");				
		dataSource = (DataSource) applicationContext.getBean("txnDataSource");
		TestUtil.initialize(applicationContext, dataSource);
		dao=new ReservationDao();
	}

	@After
	public void tearDown() throws Throwable 
	{
		TestUtil.cleanup(applicationContext, dataSource);
	}

	@Test
	public void testUpdatePassengerDetail() 
	{
		try {
			dao.updatePassengerDetail("JP", "jpvel@jp.com", "yes", 1, 20000);
		} catch (MingoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testCompleteReservation() {
		try {
					dao.completeReservation(1, "1234", "hello");
		} catch (MingoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
