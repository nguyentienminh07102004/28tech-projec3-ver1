package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/building")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;
    @DeleteMapping(value = "/")
    public void deleteBuilding(@RequestBody List<Long> buildingId) {
        buildingService.deleteBuilding(buildingId);
    }
    @GetMapping(value = "/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable(value = "id") Long buildingId) {
        return buildingService.loadStaffs(buildingId);
    }
    @PostMapping(value = "/assignment")
    public void assignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        buildingService.assignmentBuilding(assignmentBuildingDTO.getBuildingId(), assignmentBuildingDTO.getStaffId());
    }
    @PostMapping(value = "/")
    public ResponseEntity<?> updateOrAddBuilding(@RequestBody BuildingDTO buildingDTO) {
        buildingService.save(buildingDTO);
        return ResponseEntity.status(200).body(new ResponseDTO());
    }
}
