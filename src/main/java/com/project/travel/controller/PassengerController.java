package com.project.travel.controller;

import com.project.travel.dto.ActivitySignupRequest;
import com.project.travel.dto.PassengerFullDetailsResponse;
import com.project.travel.dto.PassengerListResponse;
import com.project.travel.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @PostMapping("/activity-signup")
    ResponseEntity<String> passengerActivitySignUp(@RequestBody ActivitySignupRequest activitySignupRequest) {
        return ResponseEntity.ok(passengerService.activitySignUp(activitySignupRequest));
    }

    @GetMapping("/passenger-activity-details/{passengerId}")
    public ResponseEntity<PassengerFullDetailsResponse> getPassengerActivityDetails(@PathVariable Long passengerId) {
        return ResponseEntity.ok(passengerService.getPassengerAndActivityDetails(passengerId));
    }
}
