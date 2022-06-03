package hsu.bee.petra.location.service;

import hsu.bee.petra.location.entity.Area;
import hsu.bee.petra.location.entity.Sigungu;
import hsu.bee.petra.location.repository.AreaRepository;
import hsu.bee.petra.location.repository.SigunguRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
//@RequiredArgsConstructor
public class LocationService {

    private AreaRepository areaRepository;
    private SigunguRepository sigunguRepository;

    private static List<Sigungu> sigunguList;
    private static List<Area> areaList;

    @Autowired
    public LocationService(AreaRepository areaRepository, SigunguRepository sigunguRepository) {
        this.areaRepository = areaRepository;
        this.sigunguRepository = sigunguRepository;

        sigunguList = sigunguRepository.findAll();
        areaList = areaRepository.findAll();
    }


}
