package com.project.travel.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="travel_package")
public class TravelPackage {
    @Id
    @Column(name="travel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long travelId;
    private String name;
    private Long passengerCapacity;

}
