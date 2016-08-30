package com.mingo.test;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestUtil {

	public static void initialize(ApplicationContext applicationContext, DataSource dataSource) throws Throwable {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try{
			String flightPlan = "INSERT INTO mingo_txn.flight_plan (flight_plan_id, flight_start_date, flight_end_date, is_multi_city_stop, first_origin, last_destination, seat_count, public_seat_count, captive_seat_count) VALUES ('2', '2016-09-20 08:00:00', '2016-09-20 16:30:00', '1', 'new york', 'san francisco', '12', '8', '4');";
			String detail="INSERT INTO mingo_txn.flight_plan_detail (flight_plan_detail_id, origin, destination, seat_count, fare, flight_plan_id, flight_arrival_time, flight_departure_time) VALUES ('20', 'New york', 'Denver', '2', '100', '2', '2016-09-20 08:00:00', '2016-09-20 12:00:00');";
			String ticket="INSERT INTO mingo_txn.ticket_pool (ticket_pool_id, seat_number,row_number,is_window,class,seat_side,reservation_status,created_date,updated_date,flight_plan_id,flight_plan_detail_id) VALUES(20000,1,1,1,'business','left','open','2016-09-20 00:00:00', '2016-09-20 08:00:00', 2, null)";
			jdbcTemplate.update(flightPlan);
			jdbcTemplate.update(detail);
			jdbcTemplate.update(ticket);
		}
		catch(Throwable e)
		{
			System.err.println("PLEASE MAKE SURE THAT YOU HAVE GOTTEN THE DATABASE CONFIGURATION DONE");
			throw e;
		}
	}

	public static void cleanup(ApplicationContext applicationContext, DataSource dataSource) throws Throwable {
		String deleteFlightPlan = "DELETE FROM mingo_txn.flight_plan WHERE flight_plan_id=2";
		String deleteDetail = "DELETE FROM mingo_txn.flight_plan_detail WHERE flight_plan_detail_id=20";
		String deleteTicket = "DELETE FROM mingo_txn.ticket_pool WHERE flight_plan_id=2";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try{
			
			jdbcTemplate.update(deleteFlightPlan);
			jdbcTemplate.update(deleteDetail);
			jdbcTemplate.update(deleteTicket);
		}
		catch(Throwable e)
		{
			System.err.println("PLEASE MAKE SURE THAT YOU HAVE GOTTEN THE DATABASE CONFIGURATION DONE");
			throw e;
		}
	}

}
