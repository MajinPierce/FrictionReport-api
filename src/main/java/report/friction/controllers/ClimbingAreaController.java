package report.friction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import report.friction.models.ClimbingAreaEntity;
import report.friction.services.ClimbingAreaService;
import report.friction.services.ClimbingAreaServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClimbingAreaController {

    private final ClimbingAreaService climbingAreaService;

    @Autowired
    public ClimbingAreaController(ClimbingAreaServiceImpl climbingAreaService){
        this.climbingAreaService = climbingAreaService;
    };

    //TODO implement mapstruct to customize response weather data properties
    // - many are unnecessary for api consumer
    @GetMapping("/{areaName}")
    public ResponseEntity<ClimbingAreaEntity> getClimbingAreaData(@PathVariable String areaName){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        headers.add("Allow", "GET");
        return new ResponseEntity<>(climbingAreaService.getClimbingAreaData(areaName), headers, HttpStatus.OK);
    }
}
