package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingResponseDTO;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingResponseDTO> findByCondition(Map<String, String> params, List<String> typeCode);
    void deleteBuilding(List<Long> buildingId);
    ResponseDTO loadStaffs(Long buildingId);
    void assignmentBuilding(Long buildingId, List<Long> staffId);
    BuildingDTO findBuildingById(Long buildingId);
    void save(BuildingDTO buildingDTO);
}
