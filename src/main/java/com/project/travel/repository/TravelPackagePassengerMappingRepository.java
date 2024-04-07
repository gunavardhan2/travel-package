package com.project.travel.repository;

import com.project.travel.model.TravelPackagePassengerMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelPackagePassengerMappingRepository extends JpaRepository<TravelPackagePassengerMapping, Long> {

    @Query(value = "SELECT distinct travel_id FROM travel_package_passenger_mapping tppm WHERE tppm.passenger_id = :passengerId" , nativeQuery = true)
    List<Long> findPackageIdsByPassengerId(Long passengerId);

    @Query(value = "SELECT  passenger_id FROM travel_package_passenger_mapping tppm WHERE tppm.travel_id = :travelId" , nativeQuery = true)
    List<Long> findPassengerIdsByTravelId(Long travelId);
}
