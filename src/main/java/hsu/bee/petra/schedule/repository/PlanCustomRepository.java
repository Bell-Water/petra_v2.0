package hsu.bee.petra.schedule.repository;

public interface PlanCustomRepository<T> {

    T findMyPostById(long id);
}
