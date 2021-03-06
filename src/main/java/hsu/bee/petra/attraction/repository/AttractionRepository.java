package hsu.bee.petra.attraction.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import hsu.bee.petra.attraction.entity.Attraction;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    List<Attraction> findByAddressIdIn(List<Long> addressIds);
    
//    @EntityGraph(attributePaths = {"address", "sigungu", "theme"}, type= EntityGraph.EntityGraphType.LOAD)
    List<Attraction> findByNameContains(String name, Pageable page);

}
