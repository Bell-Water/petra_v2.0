package hsu.bee.petra.attraction.service;

import hsu.bee.petra.attraction.dto.AddressDto;
import hsu.bee.petra.attraction.dto.AttractionDto;
import hsu.bee.petra.attraction.dto.SurroundingAttraction;
import hsu.bee.petra.attraction.entity.Attraction;
import hsu.bee.petra.attraction.repository.AddressRepository;
import hsu.bee.petra.attraction.repository.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;
    private final AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public AttractionDto getAttraction(Long attractionId) {

        Attraction attraction = attractionRepository.findById(attractionId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 관광지 입니다."));

        return AttractionDto.convertAttractionDto(attraction);

    }

    @Transactional
    public List<AddressDto> getSurroundingAttractions(Double x, Double y, Double distance) {

        List<SurroundingAttraction> surroundingAttractions = addressRepository.getSurroundingAttraction(x, y, distance);
        HashMap<Long, AddressDto> addressDtos = new HashMap<>();

        for(SurroundingAttraction sa : surroundingAttractions) {
            addressDtos.put(sa.getId(), new AddressDto().builder()
                            .addressId(sa.getId())
                            .x(String.valueOf(sa.getX()))
                            .y(String.valueOf(sa.getY()))
                            .address(sa.getAddress())
                            .distance(sa.getDistance())
                            .build()
                    );
        }

        List<Attraction> attractions = attractionRepository.findByAddressIdIn(new ArrayList<Long>(addressDtos.keySet()));

        for(Attraction a : attractions) {
            addressDtos.get(a.getAddress().getId()).setName(a.getName());
        }

        return new ArrayList<AddressDto>(addressDtos.values());
    }
}
