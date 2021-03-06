package hsu.bee.petra.user.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import hsu.bee.petra.code.entity.TravelCode;
import hsu.bee.petra.time.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Timestamp {

	@Id
	private String id;

	private String name;
	private String nickname;
	private String introduce;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "travel_code_id")
	private TravelCode travelCode;

	public void setTravelCode(TravelCode travelCode) {
		this.travelCode = travelCode;
	}
}
