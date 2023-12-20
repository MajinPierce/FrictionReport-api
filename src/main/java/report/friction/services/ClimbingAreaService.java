package report.friction.services;

import report.friction.dto.AreaInitDTO;
import report.friction.dto.AreaMapDTO;
import report.friction.dto.ClimbingAreaDTO;

import java.util.List;

public interface ClimbingAreaService {

    List<AreaInitDTO> getAreasInit();

    List<AreaMapDTO> getAreaMapData();

    ClimbingAreaDTO getClimbingAreaData(String areaName);

}
