package com.mingo.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mingo.server.ioc.ApplicationContextProvider;
import com.mingo.server.service.FlightPlan;
import com.mingo.server.service.MingoException;
import com.mingo.server.service.TicketResponse;

public class FlightPlanDao {

	private static final Logger log = LoggerFactory
			.getLogger(FlightPlanDao.class);

	public List<FlightPlan> fetchFlightPlan(String date, String origin,
			String destination) throws MingoException {
		ApplicationContext applicationContext = ApplicationContextProvider
				.getApplicationContext();
		DataSource dataSource = (DataSource) applicationContext
				.getBean("txnDataSource");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<FlightPlan> flightList = new ArrayList<FlightPlan>();
		String query = "select b.flight_plan_id, b.flight_start_date as departure, "
				+ " b.flight_end_date as arrival,"
				+ " b.seat_count as total_seats,"
				+ " b.seat_count-ifnull(b.reserved_seat_count,0) as available_seats, "
				+ " b.first_origin, b.last_destination "
				+ " from flight_plan b inner join flight_plan_detail a on a.flight_plan_id=b.flight_plan_id where b.first_origin "
				+ " like '"
				+ origin
				+ "' and a.destination like '"
				+ destination
				+ "' and date(b.flight_start_date)=? order by departure asc;";
		try {
			log.info("Executing flight plan query with criteria {}, {}, {}",
					date, origin, destination);
			jdbcTemplate.query(
					query,
					new Object[] { date },
					(rs, rowNum) -> new FlightPlan(rs.getInt("flight_plan_id"),
							rs.getString("departure"),
							rs.getInt("total_seats"), rs
									.getInt("available_seats"), rs
									.getString("first_origin"), rs
									.getString("last_destination"))).forEach(
					flightPlan -> flightList.add(flightPlan));
		} catch (Exception e) {
			log.error("Error querying datasource ", e);
			throw new MingoException(e);
		}
		return flightList;
	}

	public List<TicketResponse> fetchTicketPool(int flightPlanId)
			throws MingoException {
		ApplicationContext applicationContext = ApplicationContextProvider
				.getApplicationContext();
		DataSource dataSource = (DataSource) applicationContext
				.getBean("txnDataSource");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TicketResponse> ticketList = new ArrayList<TicketResponse>();
		String query = "select a.ticket_pool_id, a.seat_number, a.row_number, a.class, a.seat_side from ticket_pool a where a.flight_plan_id=? and a.reservation_status='open'";
		try {
			log.info("Executing ticket pool query with criteria {}",
					flightPlanId);
			jdbcTemplate.query(
					query,
					new Object[] { flightPlanId },
					(rs, rowNum) -> new TicketResponse(rs.getInt("ticket_pool_id"),
							rs.getInt("seat_number"),
							rs.getInt("row_number"), rs
									.getString("class"), rs
									.getString("seat_side"))).forEach(
					ticket -> ticketList.add(ticket));
		} catch (Exception e) {
			log.error("Error querying datasource ", e);
			throw new MingoException(e);
		}
		return ticketList;
	}
	
	public List<TicketResponse> blockTickets(int userId, int ticketPoolId)throws MingoException
	{
		ApplicationContext applicationContext = ApplicationContextProvider
				.getApplicationContext();
		DataSource dataSource = (DataSource) applicationContext
				.getBean("txnDataSource");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TicketResponse> ticketList = new ArrayList<TicketResponse>();
		String instruction = "update ticket_pool set reservation_status='blocked', user_id=? where ticket_pool_id=?;";
		String query="select a.ticket_pool_id, a.seat_number, a.row_number, a.class, a.seat_side from ticket_pool a where reservation_status='blocked' and user_id=?;";
		try {
			log.info("Blocking ticket in pool with instruction with userId {} and ticketPoolid {}",
					userId, ticketPoolId);
			jdbcTemplate.update(instruction, new Object[]{userId,ticketPoolId});
			log.info("Retreiving blocked tickets in pool with userId {}", userId);
			jdbcTemplate.query(
					query,
					new Object[] { userId },
					(rs, rowNum) -> new TicketResponse(rs.getInt("ticket_pool_id"),
							rs.getInt("seat_number"),
							rs.getInt("row_number"), rs
									.getString("class"), rs
									.getString("seat_side"))).forEach(
					ticket -> ticketList.add(ticket));
		} catch (Exception e) {
			log.error("Error querying datasource ", e);
			throw new MingoException(e);
		}
		return ticketList;
		}
}
