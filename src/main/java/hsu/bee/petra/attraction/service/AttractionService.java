package hsu.bee.petra.attraction.service;

import hsu.bee.petra.attraction.dto.AttractionDto;
import hsu.bee.petra.attraction.entity.Attraction;
import hsu.bee.petra.attraction.repository.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;

    @Transactional(readOnly = true)
    public AttractionDto getAttraction(Long attractionId) {

        Attraction attraction = attractionRepository.findById(attractionId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 관광지 입니다."));

        return AttractionDto.convertAttractionDto(attraction);

    }
}
