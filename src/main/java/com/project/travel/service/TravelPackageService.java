package com.project.travel.service;

import com.project.travel.dto.*;
import com.project.travel.exceptions.ResourceNotFoundException;
import com.project.travel.model.Activity;
import com.project.travel.model.Passenger;
import com.project.travel.model.TravelPackage;
import com.project.travel.model.TravelPackageDestinationMapping;
import com.project.travel.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class TravelPackageService {
    @Autowired
    TravelPackageRepository travelPackageRepository;
    @Autowired
    TravelPackageDestinationMappingRepository travelPackageDestinationMappingRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    TravelPackagePassengerMappingRepository travelPackagePassengerMappingRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public TravelPackageResponse findByTravelId(Long travelId) {
        TravelPackage travelPackage = travelPackageRepository.findByTravelId(travelId);
        if(travelPackage == null)
            throw new ResourceNotFoundException("Travel Package " + travelId + " not found");
        List<TravelPackageDestinationMapping> travelPackageDestinationMappings = travelPackageDestinationMappingRepository.findByTravelId(travelId);
        List<Long> destinationIds = new ArrayList<>();
        List<DestinationDto> destinations = new ArrayList<>();
        travelPackageDestinationMappings.forEach(travelPackageDestinationMapping ->{
            Long destinationId = travelPackageDestinationMapping.getDestination().getDestinationId();
            destinationIds.add(destinationId);
            DestinationDto destination = DestinationDto.builder()
                    .destinationId(destinationId)
                    .name(travelPackageDestinationMapping.getDestination().getName()).build();
            destinations.add(destination);
        });
        var mapper = new ModelMapper();
        List<Activity> activities = activityRepository.findByDestinationId(destinationIds);
        destinations.forEach(destination -> {
            destination.setActivities(activities.stream().filter(x->x.getDestination().getDestinationId() == destination.getDestinationId())
                    .map(y -> {
                        ActivityDto activity = mapper.map(y, ActivityDto.class);
                        activity.setAvailableSlots(y.getCapacity()-y.getPeopleEnrolled());
                        return activity;})
                    .collect(Collectors.toList()));
        });
        return TravelPackageResponse.builder()
                .packageName(travelPackage.getName())
                .packageId(travelId)
                .destinations(destinations).build();

    }

    public PassengerListResponse findTravelPackage(Long travelId) {
        TravelPackage travelPackage = travelPackageRepository.findByTravelId(travelId);
        if (travelPackage==null)
            throw new ResourceNotFoundException("Travel Package " + travelId + " not found");
        List<Long> passengerIds = travelPackagePassengerMappingRepository.findPassengerIdsByTravelId(travelId);
        if (passengerIds.isEmpty())
            throw new ResourceNotFoundException("No passengers enrolled in Travel Package " + travelId);
        List<Passenger> passengers = passengerRepository.findByPassengerId(passengerIds);
        var mapper = new ModelMapper();
        List<PassengerDto> passengerDtoList = passengers.stream().map(passenger -> {
            return mapper.map(passenger,PassengerDto.class);
        }).toList();
        return PassengerListResponse.builder()
                .packageName(travelPackage.getName())
                .passengerCapacity(travelPackage.getPassengerCapacity())
                .passengersEnrolled((long) passengerIds.size())
                .passengerList(passengerDtoList).build();
    }

}
