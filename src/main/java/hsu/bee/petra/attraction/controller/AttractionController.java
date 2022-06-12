package hsu.bee.petra.attraction.controller;

import hsu.bee.petra.attraction.dto.AddressDto;
import hsu.bee.petra.attraction.dto.AttractionDto;
import hsu.bee.petra.attraction.service.AttractionService;
import hsu.bee.petra.response.Response;
import hsu.bee.petra.response.ResponseCode;
import hsu.bee.petra.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AttractionController {


    private final AttractionService attractionService;

    @GetMapping("attraction/{attractionId}")
    public Response<AttractionDto> getAttraction(@PathVariable("attractionId") Long attractionId) {

        AttractionDto attractionDto = attractionService.getAttraction(attractionId);

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, attractionDto);
    }

    @GetMapping("attraction/surroundings")
    public Response<ArrayList<AddressDto>> getSurroundingAttractions(
            @RequestParam(value="x") Double x,
            @RequestParam(value="y") Double y,
            @RequestParam(value="distance", required=false, defaultValue= "0.5") Double distance) {


        List<AddressDto> surroundingAttractions = attractionService.getSurroundingAttractions(x, y, distance);

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, surroundingAttractions);
    }

}
