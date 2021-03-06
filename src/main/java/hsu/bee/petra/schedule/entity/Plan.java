package hsu.bee.petra.schedule.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hsu.bee.petra.attraction.entity.Attraction;
import hsu.bee.petra.schedule.dto.PlanDto;
import hsu.bee.petra.time.Timestamp;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Plan extends Timestamp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "text")
	private String memo;

	@Column(columnDefinition = "int", name = "\"order\"")
	private Integer order;

	@Column(columnDefinition = "DATE")
	private LocalDate startDate;

	@Column(columnDefinition = "DATE")
	private LocalDate endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attraction_id")
	private Attraction attraction;

	/** 양방향 매핑 */
	public void changeSchedule(Schedule schedule) {
		if(this.schedule != null) {
			this.schedule.getPlanList().remove(this);
		}
		this.schedule = schedule;
		System.out.println("==================" + schedule.getPlanList());
		if (!schedule.getPlanList().contains(this)) {
			schedule.getPlanList().add(this);
		}
	}

	/** 생성자 */
	public Plan(PlanDto planDto, Attraction attraction) {
		this.memo = planDto.getMemo();
		this.order = planDto.getOrder();
		this.startDate = planDto.getStartDate();
		this.endDate = planDto.getEndDate();
		this.attraction = attraction;
	}
}
