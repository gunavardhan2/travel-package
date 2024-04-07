package com.project.travel.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="travel_package_destination_mapping")
public class TravelPackageDestinationMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "travel_id", nullable = false)
    private TravelPackage travelPackage;
    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;
}
