package hsu.bee.petra.attraction.controller;

import hsu.bee.petra.attraction.dto.AttractionDto;
import hsu.bee.petra.attraction.service.AttractionService;
import hsu.bee.petra.response.Response;
import hsu.bee.petra.response.ResponseCode;
import hsu.bee.petra.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AttractionController {


    private final AttractionService attractionService;

    @GetMapping("attraction/{attractionId}")
    public Response<AttractionDto> getAttraction(@PathVariable("attractionId") Long attractionId) {

        AttractionDto attractionDto = attractionService.getAttraction(attractionId);

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, attractionDto);
    }

}
