package report.friction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import report.friction.dto.*;
import report.friction.services.ClimbingAreaService;
import report.friction.services.ClimbingAreaServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClimbingAreaController {

    private final ClimbingAreaService climbingAreaService;

    @Autowired
    public ClimbingAreaController(ClimbingAreaServiceImpl climbingAreaService){
        this.climbingAreaService = climbingAreaService;
    }

    @GetMapping(value={"", "/"})
    public ResponseEntity<String> getDefaultApiMessage(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain; charset=utf-8");
        headers.add("Allow", "GET");
        String body = "Friction.report API up and running";
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    @GetMapping("/init")
    public ResponseEntity<List<AreaInitDTO>> getAreasInit(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        headers.add("Allow", "GET");
        return new ResponseEntity<>(climbingAreaService.getAreasInit(), headers, HttpStatus.OK);
    }

    @GetMapping("/map")
    public ResponseEntity<List<AreaMapDTO>> getAreaMapData(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        headers.add("Allow", "GET");
        return new ResponseEntity<>(climbingAreaService.getAreaMapData(), headers, HttpStatus.OK);
    }

    @GetMapping("/{areaName}")
    public ResponseEntity<ClimbingAreaDTO> getClimbingAreaData(@PathVariable String areaName){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        headers.add("Allow", "GET");
        return new ResponseEntity<>(climbingAreaService.getClimbingAreaData(areaName), headers, HttpStatus.OK);
    }
}
