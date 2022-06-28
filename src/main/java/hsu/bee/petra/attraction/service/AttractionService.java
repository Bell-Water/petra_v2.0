package hsu.bee.petra.attraction.service;

import hsu.bee.petra.attraction.dto.AddressDto;
import hsu.bee.petra.attraction.dto.AttractionDto;
import hsu.bee.petra.attraction.dto.SurroundingAttraction;
import hsu.bee.petra.attraction.dto.AttractionDto2;
import hsu.bee.petra.attraction.entity.Attraction;
import hsu.bee.petra.attraction.repository.AddressRepository;
import hsu.bee.petra.attraction.repository.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    public List<AttractionDto2> findAttractionByName(String attraction) {

        ArrayList<AttractionDto2> attractionDtos = new ArrayList<>();
//        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "username"));
        PageRequest pageRequest = PageRequest.of(0, 5);

        List<Attraction> attractionList = attractionRepository.findByNameContains(attraction, pageRequest);
        for (Attraction a : attractionList) {
            AttractionDto2 dto = AttractionDto2
                    .builder()
                    .id(a.getId())
                    .name(a.getName())
                    .build();

            attractionDtos.add(dto);
        }

        return attractionDtos;
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

        // contentTypeId가 15인 theme.id는 76~90
        for(Attraction a : attractions) {
            if(a.getTheme().getId() >= 76 && a.getTheme().getId() <= 90) {
                addressDtos.remove(a.getAddress().getId());
            } else {
                addressDtos.get(a.getAddress().getId()).setName(a.getName());
            }
        }

        return new ArrayList<AddressDto>(addressDtos.values());
    }
}
