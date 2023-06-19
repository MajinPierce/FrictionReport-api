package report.friction.services;

import report.friction.dto.AreaInitDTO;
import report.friction.dto.AreaMapDTO;
import report.friction.dto.ClimbingAreaDTO;

import java.util.List;

public interface ClimbingAreaService {

    public List<AreaInitDTO> getAreasInit();

    public List<AreaMapDTO> getAreaMapData();

    public ClimbingAreaDTO getClimbingAreaData(String areaName);
}
