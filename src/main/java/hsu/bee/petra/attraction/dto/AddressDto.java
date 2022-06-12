package hsu.bee.petra.attraction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddressDto {

    private Long addressId;
    private String name;
    private String x;
    private String y;
    private String address;
    private String detail;
    private String zipcode;
    private Double distance;
}
