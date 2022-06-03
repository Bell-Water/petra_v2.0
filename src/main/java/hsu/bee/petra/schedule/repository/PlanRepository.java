package hsu.bee.petra.schedule.repository;

import java.util.ArrayList;
import java.util.List;

import hsu.bee.petra.attraction.entity.Attraction;
import hsu.bee.petra.schedule.dto.UserIdDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hsu.bee.petra.schedule.entity.Plan;
import hsu.bee.petra.schedule.entity.Schedule;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findBySchedule(Schedule schedule);

    List<Plan> findByScheduleAndIdIn(Schedule schedule, List<Long> planIdList);

    List<Plan> findByScheduleIn(List<Schedule> scheduleList);

    List<Plan> findByScheduleOrderByOrder(Schedule schedule);

    @Query("SELECT max(p.order) FROM Plan p WHERE p.schedule = :schedule")
    Integer findMaxOrder(@Param("schedule")Schedule schedule);

    @Query(value = "SELECT s.user_id as userId FROM plan p, schedule s WHERE p.id = :planId and s.id = p.schedule_id", nativeQuery = true)
    UserIdDto findPlanUserId(@Param("planId") long planId);
//    @Query("SELECT a FROM Plan p JOIN Attraction a ON (p.attraction_id = a.id) WHERE p IN (:planList) ORDER BY p.order")
//    List<Attraction> attractionJoinPlan(@Param("planList") List<Plan> planList);

}
