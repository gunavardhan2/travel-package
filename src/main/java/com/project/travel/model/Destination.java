package com.project.travel.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="destination")
public class Destination {
    @Id
    @Column(name="destination_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long destinationId;
    private String name;
}
