package report.friction.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import report.friction.dao.ClimbingAreaEntity;

import java.util.List;

@Repository
public interface ClimbingAreaRepository extends CrudRepository<ClimbingAreaEntity, String> {

    ClimbingAreaEntity findByAreaName(String areaName);

    List<ClimbingAreaEntity> findAll();
}
