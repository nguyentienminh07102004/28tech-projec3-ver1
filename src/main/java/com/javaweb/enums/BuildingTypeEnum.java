package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum BuildingTypeEnum {
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất")
    ;
    private String typeCode;

    BuildingTypeEnum(String typeCode) {
        this.typeCode = typeCode;
    }

    public static Map<String, String> type() {
        Map<String, String> typeCode = new TreeMap<>();
        for(BuildingTypeEnum buildingTypeEnum : BuildingTypeEnum.values()) {
            typeCode.put(buildingTypeEnum.toString(), buildingTypeEnum.typeCode);
        }
        return typeCode;
    }
}
