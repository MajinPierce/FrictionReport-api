package report.friction.services;

import report.friction.dto.AreaInitDTO;
import report.friction.dto.ClimbingAreaDTO;

import java.util.List;

public interface ClimbingAreaService {

    public List<AreaInitDTO> getAreasInit();

    public ClimbingAreaDTO getClimbingAreaData(String areaName);
}
