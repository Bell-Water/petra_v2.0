package hsu.bee.petra.schedule.repository;

import hsu.bee.petra.schedule.entity.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Repository
public class PlanCustomRepositoryImpl implements PlanCustomRepository {

    @Autowired
    EntityManager em;

    @Override
    public Plan findMyPostById(long id) {
        System.out.println("PlanCustomRepositoryImpl");
        return em.find(Plan.class, id);
    }

}
