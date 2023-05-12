package report.friction.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimbingAreaRepository extends CrudRepository<ClimbingAreaEntity, String> {

    ClimbingAreaEntity findByAreaName(String areaName);
}
