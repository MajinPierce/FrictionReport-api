package report.friction.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import report.friction.dao.ClimbingAreaEntity;

@Repository
public interface ClimbingAreaRepository extends CrudRepository<ClimbingAreaEntity, String> {

    ClimbingAreaEntity findByAreaName(String areaName);
}
