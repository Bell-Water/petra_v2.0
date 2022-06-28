package hsu.bee.petra.location.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hsu.bee.petra.location.entity.Sigungu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SigunguDto {

    private int id;
    private int areaId;
    private String areaName;
    private String name;

    public static SigunguDto convertSigunguDto(Sigungu sigungu) {
        return new SigunguDto(sigungu.getId().getId(), sigungu.getId().getAreaId()
                , sigungu.getArea().getName(), sigungu.getName());
    }

}
