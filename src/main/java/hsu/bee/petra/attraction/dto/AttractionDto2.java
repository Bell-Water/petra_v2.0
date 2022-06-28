package hsu.bee.petra.attraction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hsu.bee.petra.attraction.entity.Address;
import hsu.bee.petra.attraction.entity.Attraction;
import hsu.bee.petra.attraction.entity.Theme;
import hsu.bee.petra.location.dto.SigunguDto;
import hsu.bee.petra.location.entity.Sigungu;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AttractionDto2 {

    private long id;
    private String name;
    private String imageUrl;
    private AddressDto addressDto;
    private SigunguDto sigunguDto;
    private Theme theme;

    public static AttractionDto2 convertAttractionDto(Attraction attraction) {
        return AttractionDto2
                .builder()
                .id(attraction.getId())
                .name(attraction.getName())
                .imageUrl(attraction.getImageUrl())
                .addressDto(AddressDto.convertAddressDto(attraction.getAddress()))
//                .sigungu(attraction.getSigungu())
                .sigunguDto(SigunguDto.convertSigunguDto(attraction.getSigungu()))
//                .theme(attraction.getTheme())
                .build();
    }
}
