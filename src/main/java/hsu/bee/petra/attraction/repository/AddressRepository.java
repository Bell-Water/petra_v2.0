package hsu.bee.petra.attraction.repository;

import hsu.bee.petra.attraction.dto.SurroundingAttraction;
import hsu.bee.petra.attraction.entity.Address;
import hsu.bee.petra.attraction.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    ArrayList<Attraction> findByIdIn(List<Long> list);

    @Query(value="SELECT a.id as id, a.x as x, a.y as y, a.address as address, (6371*acos(cos(radians(:x))*cos(radians(a.y))*cos(radians(a.x)-radians(:y))+sin(radians(:x))*sin(radians(a.y)))) AS distance\n" +
            "FROM Address a\n" +
            "HAVING distance <= :distance", nativeQuery = true)
    List<SurroundingAttraction> getSurroundingAttraction(@Param(value="x")double x, @Param(value="y")double y, @Param(value="distance")double distance);

}
