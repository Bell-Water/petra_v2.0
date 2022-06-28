package hsu.bee.petra.attraction.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import hsu.bee.petra.attraction.entity.Attraction;

import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

//    @EntityGraph(attributePaths = {"address", "sigungu", "theme"}, type= EntityGraph.EntityGraphType.LOAD)
    List<Attraction> findByNameContains(String name, Pageable page);

}
