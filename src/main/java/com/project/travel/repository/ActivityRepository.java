package com.project.travel.repository;

import com.project.travel.dto.ActivityDto;
import com.project.travel.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {
    @Query(value = "SELECT * FROM activity a where a.destination_id in (:destinationIds)" , nativeQuery = true)
    List<Activity> findByDestinationId( List<Long> destinationIds);

    @Query(value = "SELECT * FROM activity a where a.activity_id = :activityId" , nativeQuery = true)
    Activity findByActivityId( Long activityId);

    @Query(value = "select EXISTS(SELECT 1 from activity where activity_id=:activityId and destination_id in (:destinationIds))" , nativeQuery = true)
    Integer checkIfActivityExistsInDestination(Long activityId, List<Long> destinationIds);

    @Query(value = "SELECT * FROM activity a where a.capacity > a.people_enrolled", nativeQuery = true)
    List<Activity> findAvailableSlotActivities();
}
