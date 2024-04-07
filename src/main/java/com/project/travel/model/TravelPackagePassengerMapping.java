package com.project.travel.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="travel_package_passenger_mapping")
public class TravelPackagePassengerMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "travel_id", nullable = false)
    private TravelPackage travelPackage;
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

}
