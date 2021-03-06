package hsu.bee.petra.schedule.repository;

import hsu.bee.petra.schedule.dto.ScheduleSigunguDto;
import hsu.bee.petra.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hsu.bee.petra.schedule.entity.Schedule;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Long countByUser(User user);

    List<Schedule> findByIdIn(List<Long> scheduleIds);

    ArrayList<Schedule> findAllByOrderByCreatedAtDesc(Pageable pageable);

//    ArrayList<Long> findIdByOrderByCreatedAtDesc(Pageable pageable);

    @Query(value="Select * FROM ( SELECT * FROM Schedule ORDER BY created_at DESC LIMIT :limit) s ORDER BY s.views DESC LIMIT :size ", nativeQuery = true)
    ArrayList<Schedule> getFamousSchedule(@Param(value="limit")int limit, @Param(value="size")int size);

//    @Query(value = "SELECT * FROM ( SELECT * FROM Schedule s ORDER BY created_at desc LIMIT :limit) s ORDER BY TIMESTAMPDIFF(DAY, s.start_date, s.end_date) DESC LIMIT :size OFFSET :page", nativeQuery = true)
//    ArrayList<Schedule> getSchedulesUsingTimediff(@Param(value="limit")int value, @Param(value= "size")int size, @Param(value="page")int page);

    @Query(value = "SELECT * FROM ( SELECT * FROM Schedule s ORDER BY created_at desc LIMIT :limit) s ORDER BY TIMESTAMPDIFF(DAY, s.start_date, s.end_date) DESC LIMIT :size", nativeQuery = true)
    ArrayList<Schedule> getSchedulesUsingTimediff(@Param(value="limit")int value, @Param(value= "size")int size);

    // n개의 스케줄에 포함된 플랜의 모든 목적지를 가져오는 쿼리
    @Query(value="SELECT scp.scheduleId, scp.planId, scp.planOrder, aa.x, aa.y \n" +
            "FROM \n" +
            "( SELECT s.id scheduleId, p.id planId, p.attraction_id as attraction_id, p.order as planOrder\n" +
            "FROM schedule s JOIN Plan p ON ( s.id = p.schedule_id )\n" +
            "WHERE s.id IN :scheduleIdList ) scp \n" +
            "JOIN\n" +
            "( SELECT ad.x as x, ad.y as y, att.id as attraction_id\n" +
            "FROM attraction att JOIN address ad ON (att.address_id = ad.id) ) aa\n" +
            "ON ( aa.attraction_id = scp.attraction_id )\n" +
            "ORDER BY scp.scheduleId, scp.planOrder", nativeQuery = true)
        List<Tuple> findXY(@Param("scheduleIdList") List<Long> scheduleIdList);

    @Query(value="select s.id as scheduleId, a.sigungu_id as sigungu, a.area_id as area, count(*) as count\n" +
            "FROM Plan p, Schedule s, Attraction a\n" +
            "WHERE s.id IN :scheduleList AND p.schedule_id = s.id and p.attraction_id = a.id and s.id\n" +
            "group by s.id, a.sigungu_id, a.area_Id;", nativeQuery = true)
    List<ScheduleSigunguDto> getSigunguFromSchedule(@Param(value="scheduleList")List<Long> scheduleIdList);
}
