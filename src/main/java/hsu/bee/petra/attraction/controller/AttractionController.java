package hsu.bee.petra.attraction.controller;

import hsu.bee.petra.attraction.dto.AttractionDto;
import hsu.bee.petra.attraction.dto.AttractionDto2;
import hsu.bee.petra.attraction.service.AttractionService;
import hsu.bee.petra.response.Response;
import hsu.bee.petra.response.ResponseCode;
import hsu.bee.petra.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AttractionController {


    private final AttractionService attractionService;

    // attractionId or 이름으로 Attraction 찾기
    @GetMapping("/attraction/{attraction}")
    public Response<AttractionDto> getAttraction(@PathVariable("attraction") String attraction) {

        try {
            long attractionId = Long.parseLong(attraction);
            AttractionDto attractionDto = attractionService.getAttraction(attractionId);

            return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, attractionDto);
        } catch(NumberFormatException e) {
            List<AttractionDto2> attractionDtos = attractionService.findAttractionByName(attraction);

            return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, attractionDtos);
        }
    }

}
