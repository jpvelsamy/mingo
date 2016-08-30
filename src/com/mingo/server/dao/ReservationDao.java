package com.mingo.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mingo.server.ioc.ApplicationContextProvider;
import com.mingo.server.service.MingoException;
import com.mingo.server.service.TicketResponse;

public class ReservationDao 
{
	private static final Logger log = LoggerFactory	.getLogger(ReservationDao.class);
	
	public TicketResponse updatePassengerDetail(String fullName, String email, String primary, int user_id, int ticket_id) throws MingoException
	{
		ApplicationContext applicationContext = ApplicationContextProvider
				.getApplicationContext();
		DataSource dataSource = (DataSource) applicationContext
				.getBean("txnDataSource");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String instruction = "update ticket_pool set full_name=?, email=?, primary_passenger=?, reservation_status='confirmed' where user_id=? and ticket_pool_id=?";
		String query = "select a.ticket_pool_id, a.seat_number,a.full_name, a.email, a.primary_passenger from ticket_pool a where a.user_id=? and a.ticket_pool_id=? and a.reservation_status='confirmed'";
		List<TicketResponse> ticketList = new ArrayList<TicketResponse>();
		try {
			log.info("Updating passenger detail ticket in pool with instruction with userId {} and ticketPoolid {}",
					user_id, ticket_id);
			jdbcTemplate.update(instruction, new Object[]{fullName, email, primary,user_id, ticket_id});
			log.info("Retreiving confirmed tickets in pool with userId {} and ticket id {}", user_id, ticket_id);
			
			jdbcTemplate.query(
					query,
					new Object[] {user_id ,ticket_id },
					(rs, rowNum) -> new TicketResponse(rs.getInt("ticket_pool_id"),	
							rs.getInt("seat_number"),
							rs.getString("full_name"), 
							rs.getString("email"), 
							rs.getString("primary_passenger"))).forEach(
					ticket ->ticketList.add(ticket));
			if(ticketList.isEmpty())
				return new TicketResponse();
			else
				return ticketList.get(0);
		} catch (Exception e) {
			log.error("Error querying datasource ", e);
			throw new MingoException(e);
		}
		
	}
	
	public boolean completeReservation(int user_id, String cardNum, String cardName) throws MingoException	
	{
		ApplicationContext applicationContext = ApplicationContextProvider
				.getApplicationContext();
		DataSource dataSource = (DataSource) applicationContext
				.getBean("txnDataSource");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String instruction1 = "update ticket_pool set reservation_status='reserved' where user_id=? and reservation_status='confirmed'";
		String instruction2 = "INSERT INTO mingo_txn.reservation (user_id,credit_card_number,credit_card_holder,updated_date) VALUES (?,?,?, NOW())";
		try {
			jdbcTemplate.update(instruction1, new Object[]{user_id});
			jdbcTemplate.update(instruction2, new Object[]{user_id,cardNum,cardName});
			return true;
		} catch (Exception e) {
			log.error("Error querying datasource ", e);
			throw new MingoException(e);
		}
	}
	
}
