package hsu.bee.petra.schedule.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulePlanXYDto {

    Long scheduleId;
    Long planId;
    Integer planOrder;
    String x;
    String y;

    public SchedulePlanXYDto(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    /*public SchedulePlanXYDto(Long scheduleId, Long planId, Integer planOrder, String x, String y) {
        this.scheduleId = scheduleId;
        this.planId = planId;
        this.planOrder = planOrder;
        this.x = x;
        this.y = y;
    }*/
}
