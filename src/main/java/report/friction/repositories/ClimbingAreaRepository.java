package report.friction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import report.friction.entities.ClimbingAreaEntity;

@Repository
public interface ClimbingAreaRepository extends JpaRepository<ClimbingAreaEntity, Integer> {

    ClimbingAreaEntity findByAreaName(String areaName);

}
