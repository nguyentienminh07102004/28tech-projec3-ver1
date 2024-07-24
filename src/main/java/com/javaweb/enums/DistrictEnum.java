package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum DistrictEnum {
    QUAN_1("Quận 01"),
    QUAN_2("Quận 02"),
    QUAN_3("Quận 03"),
    QUAN_10("Quận 10")
    ;
    private String districtName;

    DistrictEnum(String districtName) {
        this.districtName = districtName;
    }

    public static Map<String, String> type() {
       Map<String, String> district = new TreeMap<>();
       for(DistrictEnum districtEnum : DistrictEnum.values()) {
           district.put(districtEnum.toString(), districtEnum.districtName);
       }
       return district;
    }
}