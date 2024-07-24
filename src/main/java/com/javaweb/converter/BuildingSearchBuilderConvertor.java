package com.javaweb.converter;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

public class BuildingSearchBuilderConvertor {
    public static BuildingSearchBuilder toBuildingSearchBuilder(Map<String, String> params, List<String> typeCode) {
        return new BuildingSearchBuilder.Builder()
                .setName(MapUtils.getObject(params, "name", String.class))
                .setDirection(MapUtils.getObject(params, "direction", String.class))
                .setAreaFrom(MapUtils.getObject(params, "areaFrom", Integer.class))
                .setAreaTo(MapUtils.getObject(params, "areaTo", Integer.class))
                .setDistrict(MapUtils.getObject(params, "district", String.class))
                .setLevel(MapUtils.getObject(params, "level", String.class))
                .setFloorArea(MapUtils.getObject(params, "floorArea", Integer.class))
                .setManagerName(MapUtils.getObject(params, "managerName", String.class))
                .setManagerPhoneNumber(MapUtils.getObject(params, "managerPhoneNumber", String.class))
                .setRentPriceFrom(MapUtils.getObject(params, "rentPriceFrom", Integer.class))
                .setStaffId(MapUtils.getObject(params, "staffId", Long.class))
                .setRentPriceTo(MapUtils.getObject(params, "rentPriceTo", Integer.class))
                .setStreet(MapUtils.getObject(params, "street", String.class))
                .setTypeCode(typeCode)
                .setWard(MapUtils.getObject(params, "ward", String.class))
                .setNumberOfBasement(MapUtils.getObject(params, "numberOfBasement", Integer.class))
                .build();
    }
}
