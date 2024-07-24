package com.javaweb.repository.custom;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepositoryCustomer {
    List<BuildingEntity> findAll(BuildingSearchBuilder builder);
}
