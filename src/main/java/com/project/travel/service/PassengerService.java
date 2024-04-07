package com.project.travel.service;

import com.project.travel.dto.ActivitySignupRequest;
import com.project.travel.dto.PassengerActivityDetails;
import com.project.travel.dto.PassengerDetails;
import com.project.travel.dto.PassengerFullDetailsResponse;
import com.project.travel.exceptions.BadRequestException;
import com.project.travel.exceptions.ResourceNotFoundException;
import com.project.travel.model.Activity;
import com.project.travel.model.ActivityPassengerMapping;
import com.project.travel.model.Passenger;
import com.project.travel.model.Usertype;
import com.project.travel.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    TravelPackagePassengerMappingRepository travelPackagePassengerMappingRepository;

    @Autowired
    TravelPackageDestinationMappingRepository travelPackageDestinationMappingRepository;

    @Autowired
    ActivityPassengerMappingRepository activityPassengerMappingRepository;

    public String activitySignUp(ActivitySignupRequest activitySignupRequest){
        Long passengerId = activitySignupRequest.getPassengerId();
        Long activityId = activitySignupRequest.getActivityId();
        Passenger passenger = passengerRepository.findByPassengerId(passengerId);
        if(passenger == null)
            throw new ResourceNotFoundException("Passenger " + passengerId + "not found");

        Activity activity = activityRepository.findByActivityId(activityId);
        if(activity == null)
            throw new ResourceNotFoundException("Activity " + activityId + "not found");

        if(activity.getPeopleEnrolled() == activity.getCapacity())
            throw new BadRequestException("Activity capacity reached. No more passengers can be enrolled" );

        double balanceAmount = getBalanceAmount(passenger.getType(), passenger.getBalance(), activity.getCost());
        if(balanceAmount < 0)
            throw new BadRequestException("Passenger don't have sufficient balance");

        List<Long> travelPackageIds = travelPackagePassengerMappingRepository.findPackageIdsByPassengerId(passengerId);
        List<Long> destinationIds = travelPackageDestinationMappingRepository.findDestinationIdsByTravelIds(travelPackageIds);
        if(activityRepository.checkIfActivityExistsInDestination(activityId, destinationIds) == 0)
            throw new BadRequestException("Selected Activity is not available at any of the destination of the users package");
        // mapping exists code
        if(!activityPassengerMappingRepository.findByPassengerIdActivityId(passengerId,activityId).isEmpty())
            throw new BadRequestException("Passenger is already enrolled for this activity");

        // mapping not exists code
        activityPassengerMappingRepository.save(ActivityPassengerMapping.builder()
                .activity(activity)
                .passenger(passenger)
            .build());

        activity.setPeopleEnrolled(activity.getPeopleEnrolled() + 1);
        activityRepository.save(activity);

        if(!passenger.getType().equals(Usertype.PREMIUM)) {
            passenger.setBalance(balanceAmount);
            passengerRepository.save(passenger);
        }

        return "Passenger" + passengerId + " is successfully signed up for activity" + activityId;
    }

    double getBalanceAmount(Usertype passengerType, Double passengerBalance, Double activityCost) {
        return switch (passengerType) {
            case STANDARD -> passengerBalance - activityCost;
            case GOLD -> passengerBalance - (0.1 * activityCost);
            default -> 0;
        };
    }

    double amountPaid(Usertype passengerType, Double activityCost) {
        return switch (passengerType) {
            case STANDARD -> activityCost;
            case GOLD -> 0.1 * activityCost;
            default -> 0;
        };
    }

    public PassengerFullDetailsResponse getPassengerAndActivityDetails(Long passengerId)
    {
        Passenger passenger = passengerRepository.findByPassengerId(passengerId);
        if(passenger == null)
        {
            throw new ResourceNotFoundException("Passenger " + passengerId + "not found");
        }
        List<ActivityPassengerMapping> activityPassengerMappings = activityPassengerMappingRepository.findByPassengerId(passengerId);
        PassengerDetails passengerDetails = new PassengerDetails();
        passengerDetails.setId(passenger.getId());
        passengerDetails.setName(passenger.getName());
        passengerDetails.setBalance(passenger.getBalance());
        var mapper = new ModelMapper();
        List<PassengerActivityDetails> passengerActivityDetailsList = activityPassengerMappings.stream().map(y-> {
            PassengerActivityDetails passengerActivityDetails = mapper.map(y.getActivity(), PassengerActivityDetails.class);
            passengerActivityDetails.setCost(amountPaid(passenger.getType(),passengerActivityDetails.getCost()));
            return passengerActivityDetails;
        }).toList();
        return PassengerFullDetailsResponse.builder()
                .passengerDetails(passengerDetails)
                .passengerActivityDetails(passengerActivityDetailsList).build();
    }
}
