package hsu.bee.petra.attraction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import hsu.bee.petra.attraction.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddressDto {

    private Long id;
    private String x;
    private String y;
    private String address;
    private String detail;
    private String zipcode;
    private Double distance;

    public static AddressDto convertAddressDto(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .x(address.getX())
                .y(address.getY())
                .address(address.getAddress())
                .detail(address.getDetail())
                .zipcode(address.getZipcode())
                .build();
    }
}
