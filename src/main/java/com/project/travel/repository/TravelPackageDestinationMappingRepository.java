package com.project.travel.repository;

import com.project.travel.model.TravelPackage;
import com.project.travel.model.TravelPackageDestinationMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelPackageDestinationMappingRepository extends JpaRepository<TravelPackageDestinationMapping,Long> {
    @Query(value = "SELECT * FROM travel_package_destination_mapping tpdm WHERE tpdm.travel_id = :travelId" , nativeQuery = true)
    List<TravelPackageDestinationMapping> findByTravelId(Long travelId);

    @Query(value = "SELECT distinct destination_id FROM travel_package_destination_mapping tpdm WHERE tpdm.travel_id IN (:travelIds)" , nativeQuery = true)
    List<Long> findDestinationIdsByTravelIds(List<Long> travelIds);
}
