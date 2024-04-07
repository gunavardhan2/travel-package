package com.project.travel.controller;

import com.project.travel.dto.PassengerListResponse;
import com.project.travel.dto.TravelPackageResponse;
import com.project.travel.model.TravelPackage;
import com.project.travel.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travel-package")
public class TravelPackageController {
    @Autowired
    TravelPackageService travelPackageService;
    @GetMapping("/{travelId}")
    public ResponseEntity<TravelPackageResponse> findByTravelId(@PathVariable Long travelId) {
        return ResponseEntity.ok(travelPackageService.findByTravelId(travelId));
    }

    @GetMapping("/travel-details/{travelId}")
    public ResponseEntity<PassengerListResponse> getTravelDetails(@PathVariable Long travelId) {
        return ResponseEntity.ok(travelPackageService.findTravelPackage(travelId));
    }
}
