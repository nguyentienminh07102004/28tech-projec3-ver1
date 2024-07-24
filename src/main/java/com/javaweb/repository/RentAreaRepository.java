package com.javaweb.repository;

import com.javaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {
    void deleteAllByBuilding_IdIn(List<Long> buildingId);
    List<RentAreaEntity> findAllByBuilding_Id(Long buildingId);
}
