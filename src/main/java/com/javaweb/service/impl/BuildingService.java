package com.javaweb.service.impl;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConvertor;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.NotFoundException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingResponseDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssigmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuildingService implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssigmentBuildingRepository assigmentBuildingRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Override
    public List<BuildingResponseDTO> findByCondition(Map<String, String> params, List<String> typeCode) {
        BuildingSearchBuilder buildingSearchBuilder = BuildingSearchBuilderConvertor.toBuildingSearchBuilder(params, typeCode);
        List<BuildingEntity> list = buildingRepository.findAll(buildingSearchBuilder);
        return list.stream()
                .map(item -> buildingConverter.toBuildingSearchResponse(item))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBuilding(List<Long> buildingIds) {
        // xoá các bảng liên quan trước
        rentAreaRepository.deleteAllByBuilding_IdIn(buildingIds);
        assigmentBuildingRepository.deleteAllByBuilding_IdIn(buildingIds);
        // xoá building
        buildingRepository.deleteByIdIn(buildingIds);
    }

    @Override
    public ResponseDTO loadStaffs(Long buildingId) {
        List<UserEntity> listStaffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        ResponseDTO responseDTO = new ResponseDTO();
        BuildingEntity building = buildingRepository.findById(buildingId).orElseThrow(() -> new NotFoundException("Không tìm thấy toà nhà này !!!", "Vui lòng tìm lại !!!", "Rất hân hạnh cảm ơn !!!"));
        List<AssignmentBuildingEntity> assignmentBuildingEntities = building.getAssignmentBuilding();
        List<UserEntity> userAssignment = assignmentBuildingEntities.stream().map(AssignmentBuildingEntity::getUser).collect(Collectors.toList());
        List<StaffResponseDTO> staffResponseDTOList = new ArrayList<>();
        for(UserEntity user : listStaffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(user.getFullName());
            staffResponseDTO.setStaffId(user.getId());
            if(userAssignment.contains(user)) {
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOList.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOList);
        return responseDTO;
    }

    @Override
    public void assignmentBuilding(Long buildingId, List<Long> staffIds) {
        BuildingEntity building = buildingRepository.findById(buildingId).orElseThrow(NotFoundException::new);
        List<AssignmentBuildingEntity> assignmentBuilding = building.getAssignmentBuilding();
        if(!assignmentBuilding.isEmpty()) {
            // xoá các danh sách đã có
            assigmentBuildingRepository.deleteAll(assignmentBuilding);
        }
        List<AssignmentBuildingEntity> assignmentBuildingEntities = new ArrayList<>();
        for(Long staffId : staffIds) {
            AssignmentBuildingEntity assignment = new AssignmentBuildingEntity();
            assignment.setBuilding(building);
            UserEntity user = userRepository.findById(staffId).orElseThrow(NotFoundException::new);
            assignment.setUser(user);
            assignmentBuildingEntities.add(assignment);
        }
        assigmentBuildingRepository.saveAll(assignmentBuildingEntities);
    }
    @Override
    public BuildingDTO findBuildingById(Long id) {
        BuildingEntity building = buildingRepository.findById(id).orElseThrow(NotFoundException::new);
        return buildingConverter.toBuildingDTO(building);
    }

    // Sửa toà nhà và thêm toà nhà
    @Override
    public void save(BuildingDTO buildingDTO) {
        BuildingEntity building = buildingConverter.toBuildingEntity(buildingDTO);
        if(building.getId() != null) {
            // Xoá các rent Area cũ
            List<RentAreaEntity> listRentAreas = rentAreaRepository.findAllByBuilding_Id(building.getId());
            if (!listRentAreas.isEmpty()) rentAreaRepository.deleteAll(listRentAreas);
        }
        // Lưu building xuống
        BuildingEntity newBuilding = buildingRepository.save(building);
        //rentAreaRepository.deleteAllByBuilding_Id(building.getId());
        //rentAreaRepository.flush();
        if(!buildingDTO.getRentArea().isEmpty()) {
            List<Integer> list = new ArrayList<>();
            String[] rentAreaArray = buildingDTO.getRentArea().trim().split(",");
            for (String rentString : rentAreaArray) {
                list.add(Integer.valueOf(rentString.trim()));
            }
            // Lưu lại
            List<RentAreaEntity> rentAreaEntityList = new ArrayList<>();
            for (Integer rentArea : list) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setBuilding(newBuilding);
                rentAreaEntity.setValue(rentArea);
                rentAreaEntityList.add(rentAreaEntity);
            }
            rentAreaRepository.saveAll(rentAreaEntityList);
        }
    }
}
