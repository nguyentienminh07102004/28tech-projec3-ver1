package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.DistrictEnum;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingResponseDTO toBuildingSearchResponse(BuildingEntity building) {
        BuildingResponseDTO buildingSearchResponse = modelMapper.map(building, BuildingResponseDTO.class);
        Map<String, String> districtAll = DistrictEnum.type();
        String district = null;
        if(building.getDistrict() != null && !building.getDistrict().isEmpty()) {
            district = districtAll.get(building.getDistrict());
        }
        String address = building.getStreet() + ", " + building.getWard();
        if(district != null) address += ", " + district;
        buildingSearchResponse.setAddress(address);
        String rentArea = building.getRentAreaEntityList()
                .stream()
                .map(RentAreaEntity::getValue)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        buildingSearchResponse.setRentArea(rentArea);
        return buildingSearchResponse;
    }

    public BuildingDTO toBuildingDTO(BuildingEntity building) {
        BuildingDTO buildingDTO = modelMapper.map(building, BuildingDTO.class);
        // xử lý typeCode
        String type = building.getType();
        String[] typeCodes = type.split(",");
        if(typeCodes.length > 0) {
            List<String> typeCodeList = new ArrayList<>();
            for(String typeCode : typeCodes) {
                typeCodeList.add(typeCode.trim());
            }
            buildingDTO.setTypeCode(typeCodeList);
        }
        // xử lý rent area
        List<RentAreaEntity> list = building.getRentAreaEntityList();
        String rentArea = list.stream().map(RentAreaEntity::getValue).map(Object::toString).collect(Collectors.joining(", "));
        buildingDTO.setRentArea(rentArea);
        return buildingDTO;
    }

    public BuildingEntity toBuildingEntity(BuildingDTO building) {
        BuildingEntity buildingEntity = modelMapper.map(building, BuildingEntity.class);
        // xử lý typeCode
        List<String> list = building.getTypeCode();
        if(!list.isEmpty()) {
            buildingEntity.setType(String.join(",", list));
        }
        return buildingEntity;
    }
}
