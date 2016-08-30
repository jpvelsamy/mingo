select * from flight_plan where flight_plan.first_origin 
like 'New York' and date(flight_plan.flight_start_date)='2016-09-20';

select * from flight_plan_detail a inner join flight_plan b on
a.flight_plan_id=b.flight_plan_id where 
date(b.flight_start_date)='2016-09-20' and
a.origin like 'New York' and
a.destination like 'Denver';


select * from ticket_pool c inner join flight_plan_detail a on 
c.flight_plan_id=a.flight_plan_id 
inner join flight_plan b on
a.flight_plan_id=b.flight_plan_id where 
date(b.flight_start_date)='2016-09-20' and
a.origin like 'New York' and
a.destination like 'Denver' and
c.reservation_status in ('open','open after cancel');

