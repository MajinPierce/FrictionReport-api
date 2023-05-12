package report.friction.models;

import report.friction.dao.ClimbingAreaEntity;

import java.util.List;

public abstract class ClimbingAreaMapper {

    public abstract ClimbingAreaObj mapToClimbingArea(ClimbingAreaEntity climbingAreaEntity);

    public abstract List<ClimbingAreaObj> mapToClimbingArea(List<ClimbingAreaEntity> climbingAreaEntity);
}
