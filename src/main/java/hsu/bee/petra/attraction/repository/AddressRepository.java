package hsu.bee.petra.attraction.repository;

import hsu.bee.petra.attraction.entity.Address;
import hsu.bee.petra.attraction.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    ArrayList<Attraction> findByIdIn(List<Long> list);
}
