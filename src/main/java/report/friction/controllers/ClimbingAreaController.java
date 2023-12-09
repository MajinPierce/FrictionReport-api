package report.friction.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import report.friction.dto.*;
import report.friction.services.ClimbingAreaService;
import report.friction.services.ClimbingAreaServiceImpl;

import java.util.List;

@RestController
@CrossOrigin
@Tag(name="Climbing Area Controller")
public class ClimbingAreaController {

    private final ClimbingAreaService climbingAreaService;

    public ClimbingAreaController(ClimbingAreaServiceImpl climbingAreaService){
        this.climbingAreaService = climbingAreaService;
    }

    @Operation(
            summary = "Root path",
            description = "Returns a generic message on if the service is running."
    )
    @GetMapping(value={"", "/"})
    public ResponseEntity<String> getDefaultApiMessage(){
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("friction.report API up and running");
    }

    @Operation(
            summary = "Initialize Area Information",
            description = "Returns the basic information for all climbing areas needed to initialize the frontend."
    )
    @GetMapping("/init")
    public ResponseEntity<List<AreaInitDTO>> getAreasInit(){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(climbingAreaService.getAreasInit());
    }

    @Operation(
            summary = "Get Map Data",
            description = "Returns the area and current weather data needed for the map."
    )
    @GetMapping("/map")
    public ResponseEntity<List<AreaMapDTO>> getAreaMapData(){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(climbingAreaService.getAreaMapData());
    }

    @Operation(
            summary = "Get Climbing Area",
            description = "Returns all data needed to initialize a climbing area's dashboard."
    )
    @GetMapping("/{areaName}")
    public ResponseEntity<ClimbingAreaDTO> getClimbingAreaData(@PathVariable String areaName){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(climbingAreaService.getClimbingAreaData(areaName));
    }
}
