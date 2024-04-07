package com.project.travel.repository;

import com.project.travel.dto.PassengerDto;
import com.project.travel.model.Activity;
import com.project.travel.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    @Query(value="select * from passenger where passenger_id = :passengerId",nativeQuery = true)
    Passenger findByPassengerId(Long passengerId);

    @Query(value = "SELECT * FROM passenger P where P.passenger_id in (:passengerIds)" , nativeQuery = true)
    List<Passenger> findByPassengerId(List<Long> passengerIds);
}
