package com.project.travel.repository;

import com.project.travel.model.ActivityPassengerMapping;
import com.project.travel.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityPassengerMappingRepository extends JpaRepository<com.project.travel.model.ActivityPassengerMapping, Long> {
    @Query(value="select * from activity_passenger_mapping where passenger_id = :passengerId",nativeQuery = true)
    List<ActivityPassengerMapping> findByPassengerId(Long passengerId);

    @Query(value="select * from activity_passenger_mapping where passenger_id = :passengerId and activity_id = :activityId",nativeQuery = true)
    List<ActivityPassengerMapping> findByPassengerIdActivityId(Long passengerId, Long activityId);
}
