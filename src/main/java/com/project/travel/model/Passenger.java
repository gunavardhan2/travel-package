package com.project.travel.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="passenger")
public class Passenger {
    @Id
    @Column(name="passenger_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "passenger_number")
//    String passengerNumber;

    private String name;
    @Enumerated(EnumType.STRING)
    Usertype type;

    private double balance; //null for PREMIUM

}
