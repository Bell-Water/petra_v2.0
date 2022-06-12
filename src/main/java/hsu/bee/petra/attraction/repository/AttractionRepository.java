package hsu.bee.petra.attraction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hsu.bee.petra.attraction.entity.Attraction;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    List<Attraction> findByAddressIdIn(List<Long> addressIds);
}
