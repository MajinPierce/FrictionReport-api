package report.friction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import report.friction.entities.ClimbingAreaEntity;

import java.util.Optional;

@Repository
public interface ClimbingAreaRepository extends JpaRepository<ClimbingAreaEntity, Integer> {

    Optional<ClimbingAreaEntity> findByAreaName(String areaName);

}
