package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssigmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
    void deleteByUser_IdIn(List<Long> userIds);
    void deleteAllByBuilding_IdIn(List<Long> buildingIds);
}
