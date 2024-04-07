package com.project.travel.repository;

import com.project.travel.model.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage,Long> {
    @Query(value = "SELECT * FROM travel_package tp where tp.travel_id = :travelId" , nativeQuery = true)
    TravelPackage findByTravelId(Long travelId);
}
